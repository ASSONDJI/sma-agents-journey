# Étape 2 — Premier agent JADE

## Objectif
Créer un agent JADE minimal qui s'initialise, affiche un message, et se termine. Comprendre le cycle de vie géré par le framework.

## Ce que j'ai fait
- Création de `HelloAgent.java` (hérite de `jade.core.Agent`).
- Création de `Main.java` (lanceur de plateforme via `jade.wrapper`).
- Configuration de Gradle pour compiler ces classes (`sourceSets` dans `build.gradle`).
- Lancement via `./gradlew runHelloAgent` et `./gradlew runJadeGui`.

## Les challenges rencontrés et les solutions

### Challenge 1 : L'interface RMA ne s'ouvre pas avec `runHelloAgent`
**Symptôme** : Le terminal affiche `Bonjour, je suis l'agent hello` (donc l'agent fonctionne), mais la fenêtre graphique de JADE ne s'ouvre pas.
**Cause** : Le code `Main.java` utilise `rt.createMainContainer(p)` sans demander explicitement l'interface graphique.
**Solution** : J'ai utilisé la tâche Gradle `./gradlew runJadeGui` qui passe l'argument `-gui` à JADE. L'interface s'ouvre correctement.

### Challenge 2 : Avertissements `HTTPServer error` liés à Java 21
**Symptôme** : Une longue série de `AVERTISSEMENT: HTTPServer error : jade.mtp.MTPException ... module java.xml does not export ...` apparaît dans le terminal.
**Cause** : JADE utilise des classes internes du JDK (`com.sun.org.apache.xerces...`) qui ne sont plus accessibles par défaut dans les versions modernes de Java (Java 9+).
**Solution** : Ce sont des avertissements liés à la communication HTTP inter-plateformes (MTP), qui ne sont pas nécessaires pour un projet local. Je les ai ignorés. Le fonctionnement local des agents n'est pas affecté.

## Ce que j'ai compris
- Un agent JADE **hérite** de `jade.core.Agent`.
- La méthode `setup()` est appelée **automatiquement** par JADE au démarrage. Je ne l'appelle jamais moi-même.
- `doDelete()` termine proprement l'agent.
- Le `Main` est du **code hôte** (pilote la plateforme depuis l'extérieur via `jade.wrapper`). L'agent est du **code agent** (définit son comportement de l'intérieur via `jade.core`).

## Traces d'exécution
- Console : `assets/02-hello-agent-console.png`
- Interface RMA : `assets/02-hello-agent-rma.png`

## Validation
- [x] Mon agent démarre.
- [x] Je distingue un agent d'une simple classe de données.
- [x] Je peux expliquer sa responsabilité en une phrase : « Cet agent affiche son nom puis se termine proprement. »
