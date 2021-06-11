# WeatherApp
MVVM Weather App that makes requests for weather by cities and can display that information in list and detailed form.

* MVVM architecture so all three fragments can share a single ViewModel containing relevant information
* Retrofit2 to make a queried request to https://openweathermap.org using provided api key
* okhttp logging interceptors to catch malformed urls and troubleshoot http requests
* secrets_gradle_plugin to hide API key in local.properties and access it through a metadata in manifest
* viewbinding to reduce boilerplate
