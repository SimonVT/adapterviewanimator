AdapterViewAnimator
=============

A helper class for animating views within an AdapterView.

It animates adding, moving and removing views when the dataset changes. All animations are executed
simultaneously.

The adapter backing the AdapterView must have stable ids.

Usage
-----
Create an instance of the AdapterViewAnimator before changing the dataset.
Once the adapter has been notified of changes, call `AdapterViewAnimator#animate()`.

```java
AdapterViewAnimator animator = new AdapterViewAnimator(adapterView);
data.add(item);
adapter.notifyDataSetChanged();
animator.animate();
```

Custom animations can be defined by passing an instance of `AdapterViewAnimator.Callback`
to the constructor.





License
=======

    Copyright 2013 Simon Vig Therkildsen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.





