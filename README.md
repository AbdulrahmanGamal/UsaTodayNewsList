# Usa-Today 
NewsList Android app shows list of latest news on usa-today
in this app i used
- Mvp Architectural Pattern to seperate application layers so it will be easy to modify in the future and to test
- RxJava-Retrofit : to get the benifit of the observe pattern as  RxJava make  it easier to manipulate  data while in stream in background before subscriber act with it.
- i used dagger2 to create my objects 
- Picasso is used to display images in my app
- picasso downloader is used to cache images in my app so that no need to reload images from server every time 
- eventBus is used with rxJava to notify for changes  and get the latest data updates from data layer to the presentation layer

- Object-Box library is used to store favourite news in local database i used this library as it is very clear and easy to implementation 
