# RickyAndMprtyApp

An Android app created as part of a test assignment.

The project was implemented using Jetpack Compose, Hilt, MVVM, Room, Retrofit, and Clean Architecture principles.

The app displays characters from the Rick and Morty universe, with the ability to search, filter, and view details.

**Tech stack**

- View a list of characters with name, status, species, and image
- Search for characters by name
- Filter by status, gender, and species
- Refresh the list with a swipe (Swipe-to-refresh)
- Character details screen with additional information
- Data caching using **Room**
- Retrieving data from the [Rick and Morty API](https://rickandmortyapi.com/)
- Architecture Clean Architecture + MVVM - Navigation via Navigation Compose


**Project architecture **│

├── data // Data sources (Room, Retrofit, DTO, Repository)
│ ├── local // Room DAO and Entity
│ ├── remote // Retrofit API and model responce
│ └── repository // Implementation of repository interfaces
│
├── domain // Use Cases, Entities, repository interfaces
│ ├── model
│ ├── repository
│ └── usecase
│
├── presentation // ViewModel and UI on Jetpack Compose
│ ├── list // Screen with the list of characters
│ ├── detail // Character details screen
│ ├── filter //UI and filtering logic
│ └── navigation // navigation betwen screens
│
└── di // Module for Hilt (DataModule, NetworkModule and etc)
