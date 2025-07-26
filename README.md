# Java Modules Project (JPMS Demo)

[![Java](https://img.shields.io/badge/Java-21+-orange.svg)](https://www.oracle.com/java/)
[![JPMS](https://img.shields.io/badge/JPMS-Module%20System-blue.svg)](https://openjdk.java.net/projects/jigsaw/)
[![Build](https://img.shields.io/badge/Build-Maven%2FGradle-green.svg)](https://maven.apache.org/)

Демонстраційний проект Java Platform Module System (JPMS), що показує сучасну модульну архітектуру Java додатків.

## 📋 Зміст

- [Опис проекту](#опис-проекту)
- [Архітектура модулів](#архітектура-модулів)
- [Вимоги](#вимоги)
- [Встановлення та запуск](#встановлення-та-запуск)
- [Структура проекту](#структура-проекту)
- [Ключові концепції JPMS](#ключові-концепції-jpms)
- [Приклади використання](#приклади-використання)
- [Усунення проблем](#усунення-проблем)

## 📖 Опис проекту

Цей проект демонструє використання **Java Platform Module System (JPMS)**, представленої в Java 9. Проект складається з чотирьох взаємопов'язаних модулів, що реалізують паттерн **Service Provider** для різних типів бухгалтерів.

### Основні переваги модульної архітектури:
- ✅ **Сильна інкапсуляція** - модулі явно визначають що експортують
- ✅ **Надійна конфігурація** - чіткі декларації залежностей
- ✅ **Покращена безпека** - обмежений доступ між модулями
- ✅ **Кращу підтримку** - легше розуміти та розвивати додаток

## 🏗️ Архітектура модулів

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────────┐
│   main.api      │    │ main.university  │    │ main.labor.market   │
│                 │    │                  │    │                     │
│ - Accountant    │◄───┤ - AccountantImpl │    │ - AccountantLMImpl  │
│   (interface)   │    │                  │    │                     │
└─────────────────┘    └──────────────────┘    └─────────────────────┘
         ▲                       │                         │
         │                       │                         │
         │              provides │                provides │
         │                       ▼                         ▼
┌─────────────────┐    ┌──────────────────────────────────────────────┐
│ main.enterprise │    │             Service Registry                 │
│                 │    │                                              │
│ - Main.java     │◄───┤ Автоматичне знаходження та завантаження      │
│ - Bootstrap     │    │ сервісів через ServiceLoader                 │
└─────────────────┘    └──────────────────────────────────────────────┘
```

### Модулі проекту:

| Модуль | Опис | Роль |
|--------|------|------|
| **main.api** | Визначає інтерфейси та контракти | API Definition |
| **main.enterprise** | Головний модуль додатку | Application Entry Point |
| **main.university** | Реалізація для університетської системи | Service Provider |
| **main.labor.market** | Реалізація для ринку праці | Service Provider |

## 🔧 Вимоги

- **Java 21+** (або новіша версія)
- **Maven 3.6+** або **Gradle 7.0+** (опціонально)
- **IntelliJ IDEA** / **Eclipse** / **VS Code** з підтримкою Java

### Перевірка версії Java:
```bash
java -version
javac -version
```

## 🚀 Встановлення та запуск

### 1. Клонування репозиторію
```bash
git clone <repository-url>
cd modulesProject
```

### 2. Компіляція модулів
```bash
# Очищення попередніх збірок
rmdir /s out  # Windows
rm -rf out    # Linux/macOS

# Створення директорій
mkdir out\main.api out\main.university out\main.labor.market out\main.enterprise

# Компіляція в правильному порядку
javac -d out\main.api main.api\src\module-info.java main.api\src\main\api\*.java

javac -p out\main.api -d out\main.university main.university\src\module-info.java main.university\src\main\university\*.java

javac -p out\main.api -d out\main.labor.market main.labor.market\src\module-info.java main.labor.market\src\main\labor\market\*.java

javac -p out\main.api;out\main.university;out\main.labor.market -d out\main.enterprise main.enterprise\src\module-info.java main.enterprise\src\main\enterprise\*.java
```

### 3. Створення JAR файлів
```bash
mkdir out\artifacts

jar --create --file out\artifacts\main.api.jar -C out\main.api .
jar --create --file out\artifacts\main.university.jar -C out\main.university .
jar --create --file out\artifacts\main.labor.market.jar -C out\main.labor.market .
jar --create --file out\artifacts\main.enterprise.jar --main-class main.enterprise.Main -C out\main.enterprise .
```

### 4. Запуск додатку
```bash
cd out\artifacts
java -p . -m main.enterprise
```

## 📁 Структура проекту

```
modulesProject/
├── 📁 main.api/                    # API модуль
│   ├── 📁 src/
│   │   ├── 📄 module-info.java     # Модульний дескриптор
│   │   └── 📁 main/api/
│   │       └── 📄 Accountant.java  # Інтерфейс бухгалтера
│   └── 📄 main.api.iml
│
├── 📁 main.enterprise/             # Головний модуль
│   ├── 📁 src/
│   │   ├── 📄 module-info.java
│   │   ├── 📁 main/enterprise/
│   │   │   └── 📄 Main.java        # Точка входу
│   │   └── 📁 META-INF/
│   │       └── 📄 MANIFEST.MF
│   └── 📄 main.enterprise.iml
│
├── 📁 main.university/             # Університетська реалізація
│   ├── 📁 src/
│   │   ├── 📄 module-info.java
│   │   └── 📁 main/university/
│   │       └── 📄 AccountantImpl.java
│   └── 📄 main.university.iml
│
├── 📁 main.labor.market/           # Реалізація для ринку праці
│   ├── 📁 src/
│   │   ├── 📄 module-info.java
│   │   └── 📁 main/labor/market/
│   │       └── 📄 AccountantLMImpl.java
│   └── 📄 main.labor.market.iml
│
├── 📁 out/                         # Скомпільовані файли
│   ├── 📁 artifacts/               # JAR файли
│   └── 📁 production/              # .class файли
│
├── 📁 .idea/                       # IntelliJ IDEA налаштування
├── 📄 .gitignore
├── 📄 modulesProject.iml
└── 📄 README.md
```

## 🧩 Ключові концепції JPMS

### 1. Module Descriptor (`module-info.java`)

Кожен модуль має файл `module-info.java` що визначає:

```java
module main.university {
    requires main.api;              // Залежність від іншого модуля
    exports main.university;        // Експорт пакету
    opens main.university;          // Відкриття для рефлексії
    provides main.api.Accountant    // Надання сервісу
        with main.university.AccountantImpl;
}
```

### 2. Service Provider Pattern

**Постачальник сервісу:**
```java
// main.university/module-info.java
provides main.api.Accountant with main.university.AccountantImpl;
```

**Споживач сервісу:**
```java
// main.enterprise/Main.java
ServiceLoader<Accountant> loader = ServiceLoader.load(Accountant.class);
for (Accountant accountant : loader) {
    accountant.doJob();
}
```

### 3. Модульний шлях vs Classpath

**Традиційний спосіб (Classpath):**
```bash
java -cp "lib/*" com.example.Main
```

**Модульний спосіб (Module Path):**
```bash
java -p "modules" -m module.name/com.example.Main
```

## 💡 Приклади використання

### Додавання нового модуля

1. **Створіть структуру:**
```
main.newmodule/
├── src/
│   ├── module-info.java
│   └── main/newmodule/
│       └── NewAccountantImpl.java
```

2. **Визначте модуль:**
```java
module main.newmodule {
    requires main.api;
    provides main.api.Accountant 
        with main.newmodule.NewAccountantImpl;
}
```

3. **Реалізуйте інтерфейс:**
```java
public class NewAccountantImpl implements Accountant {
    @Override
    public void doJob() {
        System.out.println("New module accountant working!");
    }
}
```

### Перевірка модулів

```bash
# Список всіх модулів
java --list-modules

# Опис конкретного модуля
java --describe-module main.enterprise

# Граф залежностей
java --show-module-resolution -p . -m main.enterprise
```

## 🔧 Усунення проблем

### ❌ Error: Module not found

**Проблема:** `java.lang.module.FindException: Module main.enterprise not found`

**Рішення:**
```bash
# Перевірте що JAR файли в module path
ls -la out/artifacts/

# Перевірте наявність module-info.class
jar --list --file main.enterprise.jar | grep module-info
```

### ❌ Unsupported major.minor version

**Проблема:** `Unsupported major.minor version 65.0`

**Рішення:**
- Оновіть Java до версії 21+
- Або перекомпілюйте проект з вашою версією Java

### ❌ Service provider not found

**Проблема:** ServiceLoader не знаходить реалізації

**Рішення:**
```bash
# Перевірте module-info.java файли
# Переконайтеся що provides/uses правильно налаштовані
```

### ❌ Module graph issues

**Проблема:** Циклічні залежності або проблеми з module path

**Рішення:**
```bash
# Використайте детальний вивід
java --show-module-resolution -p . -m main.enterprise
```

## 🤝 Внесок у проект

1. Fork репозиторій
2. Створіть feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit зміни (`git commit -m 'Add some AmazingFeature'`)
4. Push до branch (`git push origin feature/AmazingFeature`)
5. Створіть Pull Request

## 📄 Ліцензія

Цей проект розповсюджується під ліцензією MIT. Дивіться файл `LICENSE` для деталей.

## 📞 Контакти

Ваше ім'я - [@your_twitter](https://twitter.com/your_twitter) - email@example.com

Посилання на проект: [https://github.com/yourusername/modulesProject](https://github.com/yourusername/modulesProject)

## 🙏 Подяки

- [Oracle Java Documentation](https://docs.oracle.com/en/java/javase/21/docs/api/)
- [Project Jigsaw](https://openjdk.java.net/projects/jigsaw/)
- [Java Module System Guide](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)

---

⭐ **Поставте зірочку якщо проект був корисним!**