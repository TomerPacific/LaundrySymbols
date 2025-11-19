# LaundrySymbols - Copilot Instructions

## Repository Overview

**Purpose**: An Android application written in Kotlin that displays different laundry care symbols with explanations. The app helps users understand washing, bleaching, drying, and ironing symbols found on clothing labels.

**Key Technologies**:
- Language: Kotlin
- UI Framework: Jetpack Compose (migrated from XML views)
- Build System: Gradle 8.11.1 with Kotlin DSL
- Architecture: MVVM with Fragments
- Android SDK: Min 21, Target 35, Compile 35
- JDK: 17 (required)

**Repository Size**: ~9.4MB (includes image assets for laundry symbols)
**Code Size**: 13 Kotlin source files (~772 total lines of code)

## Project Structure

### Directory Layout
```
LaundrySymbols/
├── .github/workflows/        # CI/CD pipelines
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/tomerpacific/laundry/
│   │   │   │   ├── activity/         # MainActivity (entry point)
│   │   │   │   ├── fragment/         # UI fragments (Compose-based)
│   │   │   │   ├── viewmodel/        # MainViewModel
│   │   │   │   ├── model/            # Data models
│   │   │   │   ├── LaundrySymbolsRepository.kt  # Symbol data provider
│   │   │   │   ├── LaundrySymbolsConstants.kt   # App constants
│   │   │   │   ├── Type.kt           # Compose font definitions
│   │   │   │   └── StyledText.kt     # Compose text utilities
│   │   │   ├── res/
│   │   │   │   ├── drawable/         # Laundry symbol images (50 PNGs)
│   │   │   │   ├── drawable-xxhdpi/  # High-res symbol images
│   │   │   │   ├── layout/           # activity_main.xml (ViewBinding)
│   │   │   │   ├── values/           # strings.xml, colors.xml, styles.xml
│   │   │   │   ├── values-{lang}/    # i18n (de, fr, it, pt)
│   │   │   │   ├── mipmap-*/         # App launcher icons
│   │   │   │   └── font/             # Bangers font family
│   │   │   └── AndroidManifest.xml
│   │   └── androidTest/
│   │       └── java/.../LaundryCategoriesTest.kt  # Compose UI tests
│   ├── build.gradle.kts      # App module build config
│   └── proguard-rules.pro    # ProGuard configuration (mostly default)
├── gradle/
│   ├── libs.versions.toml    # Version catalog for dependencies
│   └── wrapper/              # Gradle wrapper files
├── build.gradle.kts          # Root build configuration
├── settings.gradle.kts       # Project settings
├── gradle.properties         # Gradle JVM settings
└── README.md
```

### Key Source Files (in order of importance)

1. **MainActivity.kt** (100 lines): App entry point, handles in-app updates, uses Compose with ViewBinding
2. **LaundryCategoriesFragment.kt** (197 lines): Main screen showing 4 categories + "How To" section
3. **LaundryCategoryFragment.kt** (147 lines): Displays symbols grid for selected category
4. **HowToDoLaundryFragment.kt** (166 lines): Educational content about laundry
5. **LaundrySymbolFragment.kt** (78 lines): Detail view for individual symbols
6. **MainViewModel.kt** (71 lines): ViewModel managing app state and navigation
7. **LaundrySymbolsRepository.kt** (89 lines): Provides symbol data for 4 categories
8. **Model classes**: LaundrySymbol.kt (5 lines), HowToDoLaundryCategory.kt (8 lines)
9. **Constants**: LaundrySymbolsConstants.kt (8 lines) - Keys for fragment arguments

## Architecture & Important Notes

**Architecture Pattern**: MVVM with Fragment-based navigation
- All fragments use Jetpack Compose for UI (ComposeView with setContent)
- MainActivity uses ViewBinding with Compose MaterialTheme wrapper
- MainViewModel is shared across fragments using `activityViewModels()`
- Fragment transactions managed by MainActivity's FragmentManager

**Compose Integration**:
- Fragments extend Fragment and return ComposeView from onCreateView()
- Uses Material3 components (Scaffold, Card, Text, etc.)
- Custom font: Bangers (defined in Type.kt)
- AndroidViewBinding for activity_main.xml integration
- Test tags used extensively for UI testing (e.g., `testTag("washing category")`)

**Data Flow**:
- LaundrySymbolsRepository creates symbol lists with localized strings
- ViewModel calls repository methods and handles fragment navigation
- Fragments observe/access ViewModel data directly

**Navigation**:
- Fragment arguments passed via Bundle (e.g., LAUNDRY_CATEGORY_KEY)
- Back stack managed by FragmentManager
- MainActivity overrides onBackPressed() for back navigation

## Build & Test Instructions

### Prerequisites
- **JDK 17** (required) - Check with `java -version`
- Android SDK with API 35 (compile SDK)
- **IMPORTANT**: Always run `chmod +x ./gradlew` before any Gradle commands

### Build Commands (in order)

1. **Make Gradlew Executable** (ALWAYS FIRST):
   ```bash
   chmod +x ./gradlew
   ```

2. **Run Tests** (unit + instrumented):
   ```bash
   ./gradlew test
   ```
   - Runs unit tests (if any exist in `app/src/test/`)
   - Time: ~30-60 seconds after Gradle daemon starts
   - Android instrumented tests require emulator/device: `./gradlew connectedAndroidTest`

3. **Build Debug APK**:
   ```bash
   ./gradlew assemble
   ```
   or specifically:
   ```bash
   ./gradlew assembleDebug
   ```
   - Time: ~60-120 seconds for clean build
   - Output: `app/build/outputs/apk/debug/app-debug.apk`

4. **Build Release AAB** (for Play Store):
   ```bash
   ./gradlew bundleRelease
   ```
   - Requires signing configuration (not in repo)
   - Output: `app/build/outputs/bundle/release/app-release.aab`
   - ProGuard minification enabled for release builds

5. **Clean Build**:
   ```bash
   ./gradlew clean
   ```

### Important Build Notes

- **First-time build**: Gradle downloads dependencies (~30-60 seconds)
- **No explicit lint task** in CI, but you can run: `./gradlew lint`
- **Test task** runs both unit tests and lint checks automatically
- **Build order matters**: The CI workflow runs `test` then `assemble`
- **Version catalog**: Dependencies managed in `gradle/libs.versions.toml`
- **Build features enabled**: DataBinding, Compose, ViewBinding

### Android SDK Requirements

If building locally without full Android SDK:
- The build may fail with plugin resolution errors (e.g., "Plugin [id: 'com.android.application'] was not found")
- CI environment (GitHub Actions) uses `actions/setup-java@v1` with Java 17
- CI sets up Android SDK automatically via Gradle Android plugin

## CI/CD Workflows

### android_build.yml (Pull Requests)
Triggers: On every pull request
Steps:
1. Checkout code
2. Setup JDK 17
3. `chmod +x ./gradlew`
4. `./gradlew test` - Run tests
5. `./gradlew assemble` - Build debug APK

**Key Point**: Tests run BEFORE building. If tests fail, build doesn't happen.

### android_publish.yml (Release Branches)
Triggers: After successful android_build.yml on `release/**` branches
Steps:
1. Checkout code
2. Setup JDK 17
3. `chmod +x ./gradlew`
4. `./gradlew test`
5. `./gradlew build` - Full build
6. `./gradlew bundleRelease` - Create AAB
7. Sign AAB with keystore (secrets required)
8. Upload to Google Play Store production track

**Secrets Required**: SIGN_KEY, ALIAS, STORE_KEY_PASSWORD, KEY_PASSWORD, SERVICE_ACCOUNT

## Testing

### Test Files
- **LaundryCategoriesTest.kt**: Compose UI tests using `createAndroidComposeRule`
- Tests use `onNodeWithText()`, `onNodeWithTag()`, `performClick()`, `assertIsDisplayed()`
- Example tags: "washing category", "bleaching category", "Do Not Wash"

### Running Tests
```bash
./gradlew test  # Unit tests
./gradlew connectedAndroidTest  # Instrumented tests (requires device/emulator)
```

### Test Patterns
- Activity-based Compose tests extend `@RunWith(AndroidJUnit4::class)`
- Use `composeTestRule.activity.getString(R.string.xxx)` for localized strings
- Navigation tested by clicking and asserting next screen elements

## Common Development Tasks

### Adding a New Laundry Symbol
1. Add PNG drawable to `app/src/main/res/drawable/` or `drawable-xxhdpi/`
2. Add string resources in `res/values/strings.xml` (and translations in `values-{lang}/`)
3. Update method in `LaundrySymbolsRepository.kt` (e.g., `createWashingSymbols()`)
4. No ViewModel changes needed - repository methods return Lists

### Adding a New Fragment
1. Create Fragment class in `app/src/main/java/com/tomerpacific/laundry/fragment/`
2. Override `onCreateView()` returning `ComposeView(requireContext()).apply { setContent { ... } }`
3. Use `by activityViewModels()` to access shared ViewModel
4. Add navigation logic in MainViewModel or calling fragment

### Modifying Compose UI
- All UI is Compose-based (no XML layouts except activity_main.xml)
- Use Material3 components from `androidx.compose.material3`
- Apply `Scaffold(contentWindowInsets = WindowInsets.safeContent)` for proper insets
- Use `Modifier.testTag()` for testable elements

### Localization
- Add strings to `res/values-{lang}/strings.xml`
- Supported: German (de), French (fr), Italian (it), Portuguese (pt)
- Use `context.getString(R.string.xxx)` in repository/fragments
- Use `stringResource(R.string.xxx)` in Composables

## Version Management

**App Version**: Defined in `app/build.gradle.kts`
- `versionCode = 28`
- `versionName = "2.6.1"`

**Dependency Versions**: All in `gradle/libs.versions.toml`
- Android Gradle Plugin: 8.9.1
- Kotlin: 2.0.0
- Compose Material3: 1.2.1
- Fragment KTX: 1.4.0
- AppCompat: 1.4.0

## ProGuard/R8 Configuration

Release builds use R8 with:
- `isMinifyEnabled = true`
- `isShrinkResources = true`
- Rules in `app/proguard-rules.pro` (mostly default Android rules)
- Debug symbols: `debugSymbolLevel = "SYMBOL_TABLE"`

## Common Issues & Solutions

1. **Permission Denied on gradlew**: Always run `chmod +x ./gradlew` first
2. **JDK Version Mismatch**: Ensure JDK 17 is active (`java -version`)
3. **Missing Android SDK**: CI handles this; local builds need Android Studio or command-line tools
4. **Dependency Resolution**: Check `gradle/libs.versions.toml` for correct versions
5. **Compose Preview Not Working**: Ensure `@Preview` functions are standalone (not in fragments)
6. **Test Failures**: Instrumented tests require emulator; use `./gradlew test` for unit tests only

## Important Conventions

- **Package**: `com.tomerpacific.laundry`
- **Namespace**: `com.tomerpacific.laundry` (in build.gradle.kts)
- **Code Style**: `kotlin.code.style=official` (in gradle.properties)
- **File Naming**: PascalCase for classes, camelCase for files
- **Constants**: UPPER_SNAKE_CASE in LaundrySymbolsConstants.kt
- **Resource Naming**: snake_case (e.g., `do_not_wash_symbol`)

## Performance Notes

- App uses in-app update manager (Google Play Core library)
- Images are PNGs (not vector) - consider adding WebP versions for smaller size
- LazyVerticalGrid in LaundryCategoryFragment for efficient symbol grid display
- No network calls - all data is local (hardcoded in repository)

## Trust These Instructions

**These instructions have been validated by exploring the codebase**. Only perform additional searches if:
1. Information here is incomplete or unclear
2. You encounter errors not documented here
3. The codebase has changed significantly since these instructions were written

When in doubt, follow the CI workflow steps exactly as documented above. The CI workflow is the source of truth for what works.
