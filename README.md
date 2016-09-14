# KenWeatherAppFinished
This is the Repo for my finished weather app.
Ken Weather App Project Description

The purpose of the Ken Weather App Project is to familiarize myself with SQLite Database, Android System, and the Google Android Studio. The Ken Weather App Project has been built from scratch with the help of Udacity Android App Development Tutorials and Stack Overflow. The KenWeatherApp is similar to the one that was taught in the tutorials. However, I made some major changes to make the app quite different from Udacity’s the WeatherApp. This description document is going to explain the purposes of each class in the project.

WeatherContract Class

A contract is an agreement between the data model and the views (i.e. UI) describing how information is stored. It contains constant strings to represent all the fields that the user interface will display. In my case, I have three tables as three inner classes with constant strings to represent different fields in them. The location, weather, and description will have the declarations of all the fields that I need on the UI.

WeatherDbHelper

The WeatherDbHelper class extends the SQLiteOpenHelper class. The WeatherDbHelper class contains and executes the actual sql code that creates the location, weather, and description tables. Also, primary key constraints, not null constraints, and other major constraints on database columns are implemented in the class by the sql code. 

WeatherProvider

The WeatherProvider class extends the Android Content Provider class. The WeatherProvider provides methods to help the app query the SQLite Database. It also provides methods such as insert, delete, and update for the app to interact with the database. Other Android applications could interact with the Weather database by using the WeatherProvider class methods or  by building URLs to query the database.

MainActivity

The MainActivity starts up the ForecastFragment class. It adds Settings item and All Your Feelings item in the menu bar which directs users to the screens that they want to go.  

ForcastFragment

The ForcastFragment class adds the refresh button to the menu bar. It starts up the main ListView weather screen. Users can click on dates on the main ListView weather screen in order to view details on those dates of the weather. It creates a cursor loader which is an implementation of the async task loader to query content provider (WeatherProvider).  

ForecastAdapter

The ForecastAdapter class inflates the list item layout. Also, it’s designed to bind data from the cursor to different views in the layout XML.

FectchWeatherTask

This class connects the application to the OpenWeatherMap Weather API, which allows the application to send request to OpenWeatherMap and get JSON formated response from the API. The class also parses the needed data in JSON format and inserta the data in the application database.

DetailFragment and DetailActivity 

The detail activity class sets the screen to the detail activity layout. Also, the DetailActivity class calls up the DetailFragment class which populates different views in the layout XML using data from cursor.  Moreover, the DetailActivity class has the method (EnterDatabase) for user to interact with the database by allowing users to enter their feelings in the database.

DescriptionActivity

The description activity class displays users’ feelings by using the TableLayout.

SettingsActivity

The settings activity class offers users choices to choose the location where they want the weather forecast. Also the class allows users to pick different units to display temperature data.

Utility 

The Utility class takes charge of formatting dates and temperatures. It also helps to get the correct art resources for different weather condition codes. 

All in all, this is my very first Android App. The development process was really interesting, and I learnt a lot by myself. However, there are still lots of interesting topics for me to explore for my next Android project such as how to create a dynamic and multi-pane user interface on Android, how to improve battery life on Android devices, and how does the Android system allocate resources to different running applications.
