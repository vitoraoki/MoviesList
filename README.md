# Overview
`MoviesList` is an app created to practice key Android development concepts such as Clean Architecture, MVVM, pagination, network requests, dependency injection, and the design of more complex layouts.
It’s a simple application that displays a list of movies currently playing in theaters. When a movie card is tapped, it shows additional details about the selected film.
All data used in the app is sourced from the [TMDB (The Movie Database) API](https://www.themoviedb.org), and all rights to the data are reserved by TMDB.

# Technologies
The technologies used in this project include:

- Dagger Hilt
- Retrofit
- Paging3
- Jetpack Compose
- Jetpack Navigation
- Kotlin Coroutines
- JUnit
- MockK

# Improvements
With more development time, some improvements could be made to the app:

- Caching of the movie list and movie details using Room Database
- Pull-to-refresh and automatic retry after pagination request errors
- More polished and refined UI layout
- Android instrumented tests

# Evidences

| Dark Mode | Light Mode |
|---|---|
| <image src='https://github.com/user-attachments/assets/2543adcb-eee6-48e0-a35c-a5897632ae55' width=300/> | <image src='https://github.com/user-attachments/assets/32a4c4ce-3bea-4cde-a506-974aff97527d' width=300/> |

| Scroll List | Open Details Screen |
|---|---|
| <video src='https://github.com/user-attachments/assets/56977a45-811a-42c0-8c27-e3149d97a5d7' width=100/> | <video src='https://github.com/user-attachments/assets/d04ea304-1b23-46d0-8a27-3990582ca37a' width=100/> |

| List Screen Error | Details Screen Error |
|---|---|
| <video src='https://github.com/user-attachments/assets/a207782b-8f8f-4fe2-a2b2-20725565e774' width=100/> | <video src='https://github.com/user-attachments/assets/8c94a0ae-a5a2-44a4-8a35-402eb9f00f49' width=100/> |
