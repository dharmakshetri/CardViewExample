# CardViewExample
Cardview and RecyclerView Example

#Used 
1. RecylerView
2. CardView
3. Glide

#what is CardView and RecyclerView
CardView is another major element introduced in Material Design.

#How to add CardView and RecyclerView on build.grade 
you can use the RecyclerView and CardView widgets on devices that run older versions of 
Android by adding the following lines to the dependencies section in your project's build.grade
file as your requiremnts

```sh
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
            })
    androidTestCompile('com.android.support.test.espresso:espresso-contrib:2.2') {
        // Necessary to avoid version conflicts
        exclude group: 'com.android.support', module: 'appcompat'
        exclude group: 'com.android.support', module: 'support-v4'
        exclude group: 'com.android.support', module: 'support-annotations'
        exclude module: 'recyclerview-v7'
    }
    compile 'com.android.support:appcompat-v7:25.0.0'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.android.support:design:25.0.0'
    testCompile 'junit:junit:4.12'
    compile 'com.google.code.gson:gson:2.2.4'
    compile 'com.android.support:cardview-v7:25.+'
    compile 'com.android.support:appcompat-v7:25.+'
}
```
#Application snapshots
---
![CardVeiw](http://i.imgur.com/fjRP6xO.png)
---
![Details](http://i.imgur.com/YcC5If5.png)

###About Me

I am an android engineer, currently located in Fremont, CA.

If you have any idea and wann chat with me, please visit my blog [PrAndroid](http://www.prandroid.com) and [CodingWorkspace](http://www.codingworkspace.com). 
More about me please visit my personal website:[DHARMAKSHETRI.COM.NP](http://dharmakshetri.com.np/).
