# Xountries
Android aplication showing a list of countries managing RecyclerView and saving favorites.

On this application i have used an api for get the complete list of countries managing pagination for load on groups of 20 items. Using DBFlow for a manage an SQLite where we store the favorite countrys using like **PrimaryKey** the country code.

## Tests
### Unit Test
There are 2 unit tests on the project.
- [ValidApi](https://github.com/fjbatresv/Xountries/blob/master/app/src/test/java/gt/com/fjbatresv/xountries/UnitTests.java#L30):  This method is used for make a request to api getting 10 elements on page 1.
- [serializerTest](https://github.com/fjbatresv/Xountries/blob/master/app/src/test/java/gt/com/fjbatresv/xountries/UnitTests.java#L51): This valid the functionallity of the ObjectSerializer class serializing an object and deserealizing it. 

### Robo Test

Exist a robo test made for run on the [Firebase Test Lab](https://firebase.google.com/products/test-lab/) 

#### Results  of robo test

You can find a three elements verifying the results of the robo tests

#### Screen Map

<center><img src="test%20results/20193001_1535/Screen%20Map.png" alt="screen map" height="500" /></center>

#### Performance Metrics

<center><img src="test%20results/20193001_1535/Performance%20metrics.png" alt="Performance Metrics" height="200" /></center>

#### Portrait Video
<a href="https://youtu.be/SinQxT4WVL4" target="_blank"><img src="https://cdn1.iconfinder.com/data/icons/logotypes/32/youtube-512.png" height="100" /></a>

## Libraries

The libraries used on this project are:

- [DBFlow](https://github.com/agrosner/DBFlow)
- [EventBus](https://github.com/greenrobot/EventBus)
- [Butter Knife](https://github.com/JakeWharton/butterknife)
- [Glide](https://github.com/bumptech/glide)
- [Circle Image View](https://github.com/hdodenhof/CircleImageView)
- [Dagger 2](https://github.com/google/dagger)
- [Retrofit](https://github.com/square/retrofit)
- [Tango Android Material Intro](https://github.com/AppIntro/AppIntro)
- [Fast Scroll RecyclerView](https://github.com/AndroidDeveloperLB/LollipopContactsRecyclerViewFastScroller)

## Contributors
- [Javier Batres](https://github.com/fjbatresv) (Developer)
- [Rodrigo Cano](https://github.com/rodjcano) (Logo designer)