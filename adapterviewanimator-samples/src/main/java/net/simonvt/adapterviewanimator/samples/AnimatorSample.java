package net.simonvt.adapterviewanimator.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.simonvt.adapterviewanimator.AdapterViewAnimator;

public class AnimatorSample extends Activity {

  private MyAdapter adapter;

  private int itemNumber;

  private List<Item> data = new ArrayList<Item>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.content);

    adapter = new MyAdapter(data);

    final ListView list = (ListView) findViewById(android.R.id.list);
    list.setEmptyView(findViewById(android.R.id.empty));
    list.setAdapter(adapter);

    findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (data.isEmpty()) return;

        AdapterViewAnimator animation = new AdapterViewAnimator(list);
        final int removeItem = (int) (Math.random() * data.size());
        data.remove(removeItem);
        adapter.notifyDataSetChanged();
        animation.animate();
      }
    });

    findViewById(R.id.shuffle).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        AdapterViewAnimator animation = new AdapterViewAnimator(list);
        Collections.shuffle(data);
        adapter.notifyDataSetChanged();
        animation.animate();
      }
    });

    findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        AdapterViewAnimator animation = new AdapterViewAnimator(list);
        final int insertAt = (int) (Math.random() * data.size());
        data.add(insertAt, new Item(++itemNumber));
        adapter.notifyDataSetChanged();
        animation.animate();
      }
    });
  }

  private class Item {

    long itemId;

    String text;

    private Item(long itemId) {
      this.itemId = itemId;
      text = "Item #" + itemId;
    }
  }

  private class MyAdapter extends BaseAdapter {

    private List<Item> listItems;

    private MyAdapter(List<Item> listItems) {
      this.listItems = listItems;
    }

    @Override public int getCount() {
      return listItems.size();
    }

    @Override public Object getItem(int position) {
      return listItems.get(position);
    }

    @Override public long getItemId(int position) {
      return listItems.get(position).itemId;
    }

    @Override public boolean hasStableIds() {
      return true;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      View v = convertView;
      if (v == null || !AdapterViewAnimator.allowRecycling(convertView)) {
        v = LayoutInflater.from(AnimatorSample.this)
            .inflate(android.R.layout.simple_list_item_1, parent, false);
      }

      Item item = data.get(position);

      ((TextView) v).setText(item.text);

      return v;
    }
  }
}
