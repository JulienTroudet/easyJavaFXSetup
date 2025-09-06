# MyApp - JavaFX Demo Project

## 🎯 Objective
**MyApp** is a demo project designed to provide a solid foundation for modern JavaFX applications. It includes all the essential configurations to simplify development, theme management, and multi-platform distribution.

![img.png](images/img.png "screenshot")


![img_fr.png](images/img_fr.png "screenshot fr")
## 🚀 Main Features
- **JavaFX Framework**: A robust base for building a smooth and modern UI.
- **Persistence of user settings** : 
  - Window position on the screen
  - Theme used
  - Text input
- **Built-in Themes**:
    - 🌙 Dark Mode
    - ☀️ Light Mode
- **Internationalization (i18n) Support**: Ready for multiple languages.
- **Advanced UI Library**: **AtlantaFX** pre-configured for an enhanced application look.
- **Complete Gradle Setup**: Advanced configuration for compiling, packaging, and distributing the application easily.
- **Automated Deliverable Generation**:
    - Windows: `.exe` and `.msi`
    - Linux: `.deb` and `.rpm`
    - macOS: `.dmg`
- **GitHub Actions Workflows**:
    - 📦 Automatic builds on tag
    - 🚀 GitHub releases with all deliverables

## 📦 Installation & Usage
### 1️⃣ Prerequisites
- **JDK 21** (Temurin recommended)
- **Gradle** (if not included in the wrapper)
- **Git** (to clone the repository)

### 2️⃣ Clone the Project
```sh
git clone https://github.com/your-repo/MyApp.git
cd MyApp
```

### 3️⃣ Run the Application in Development Mode
```sh
./gradlew run
```

## 🔧 Build and Generate Deliverables
To generate an executable for your OS, use one of the following commands:
```sh
./gradlew buildExeForWindows    # Windows EXE
./gradlew buildMsiForWindows    # Windows MSI
./gradlew buildDebForLinux      # Linux DEB
./gradlew buildRpmForLinux      # Linux RPM
./gradlew buildDmgForMacOS      # macOS DMG
```

There is also an Inno Setup file provided to create a Windows Installer.

## 🛠️ Contribution
Contributions are welcome! Feel free to suggest improvements via issues or pull requests.

## 📄 License
This project is licensed under the **MIT** License. You are free to use and modify it as needed.
The default project icon was generated at https://game-icons.net/

---
💡 **MyApp** allows you to start a JavaFX project without worrying about complex configurations. Enjoy and create powerful applications right from the start! 🚀
