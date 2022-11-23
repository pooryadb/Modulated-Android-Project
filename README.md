# Modulated-Android-Project

This is a base Android-Project, focused on Modularization-Patterns.

## modules & dependencies

- :app

```
  | :core:baseUi 
  | :core:common
  | :core:data
  | :core:domain
  | :core:model
  | :core:network
  | :feature:product

  (should use all lower-level modules)
```

- :core:baseUi

```
    | :core:common
```

- :core:common

- :core:data

```
  | :core:common
  | :core:model
  | :core:network
```

- :core:domain

```
  | :core:data
  | :core:model
```

- :core:model

- :core:network

```
  | :core:common
```

- :feature:product

```
  | :core:baseUi
  | :core:common
  | :core:data
  | :core:domain
  | :core:model
```

## Refrence

- [Guide to Android app modularization](https://developer.android.com/topic/modularization)
- [Now in Android App](https://developer.android.com/topic/modularization)
- [mvvm arch sample](https://github.com/faruktoptas/mvvm-arch-sample)