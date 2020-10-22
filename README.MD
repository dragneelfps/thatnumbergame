## Running
1. Start backend server `./gradlew run`
2. Start continuous build `./gradlew classes -t`
3. (Optional) To make use of webpack's hot reload of your frontend, start devServer along with classes build (instead of step 2)
`./gradlew classes frontendBrowserDevelopmentRun -t`

Ktor server running on port 8080
Webpack DevServer running on port 300 and set-up to proxy all requests to port 8080
So both, `http://localhost:3000/game/new` & `http://localhost:3000/game/new` end up working the same.
