# Todo List for Aptoide Android Application

## Project Overview

This project aims to develop an Android application for the Aptoide platform. The application fetches data from the Aptoide API to display a list of applications and their details. It incorporates various features such as API integration, UI development, offline support, notification scheduling, and code quality maintenance.

## APK for testing
You can download the latest version of the APK file for immediate testing from the following:

[Download APK](https://example.com/path/to/apk/file)

## Task list

### Project Setup

- [x] Create a new Android project in Android Studio.
- [x] Set up the project structure including activities, layouts, and resources.

### API Integration

- [x] Set up networking library (e.g., Retrofit) to fetch data from the provided Aptoide API endpoint.
- [x] Create models to represent the JSON response.
- [x] Implement logic to fetch and parse the JSON response from the API.

### UI Development

- [x] Design the main screen to display the list of applications fetched from the API.
- [x] Create a separate layout for the "App Details" screen.
- [x] Implement navigation to the "App Details" screen when an app is tapped in the list.

### App Details Screen

- [x] Display additional information about the selected app.
- [x] Implement a "Download" button.
- [] Show a dialog indicating download functionality is not available in demo mode when the "Download" button is clicked.

### Bonus Task - Notification

- [x] Implement logic to schedule and send notifications every 30 minutes.
- [x] Ensure notifications are sent even when the app is in the background or foreground.

### Offline Support

- [x] Implement caching mechanism to store fetched data locally.
- [x] Modify the UI to display cached data when there's no internet connection.

### Code Quality and Comments

- [X] Write clear and concise code comments explaining complex logic, architectural choices, and major functions.
- [x] Ensure the code follows best practices and is maintainable.

### Documentation

- [] Create a README.md file detailing the project, including setup instructions, project structure, and usage.
- [] Mention any known bugs or issues.
- [] Describe what improvements would be made if given more time.
- [] Propose additional features to enhance the app further.

### Testing

- [] Test the app thoroughly to ensure all features work as expected.
- [] Perform both unit tests and UI tests to cover different aspects of the application.

### APK Generation

- [] Generate an APK file for immediate testing of the application.
- [] Provide the APK file along with the source code in the repository.

### Submission

- [] Submit the project via GitHub, ensuring all code is well-commented and documented.
- [] Include the APK file and any additional documentation required for evaluation.

## Tech stack

- Core: AndroidX Core KTX _(Kotlin extensions)_.
- Lifecycle: AndroidX Lifecycle _(lifecycle management)_.
- Compose: Jetpack Compose UI _(modern UI toolkit)_.
- Navigation: AndroidX Navigation _(simplified navigation - management)_.
- Http: Retrofit _(type-safe HTTP client)_.
- Image Loading: Coil Compose _(lightweight image loader)_.
- Worker: AndroidX Work _(background task execution)_.
- Room: AndroidX Room _(SQLite abstraction layer)_.
- DI: Hilt Android _(dependency injection framework)_.

## To improve in the future and known bugs

- Have a proper image for the hero on the details page (portrait or 4:3 instead of landscape images).
- Add screens for no result if the app by id is not found.
- Consider using shimmering skeleton instead of spinner loaders on every single app for better UI/UX.
- Improve error handling with proper error codes and user facing text
- Resume download when the device has no internet connection but restores connection.
- Differentiate between WiFi and mobile data connections for informing the user about data consumption during downloads.
- Improve existing composes to reduce possible code duplications
