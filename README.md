<div>
<img src="./corda_logo.png" width="40%" alt="Corda"/>
<img src="./accord_logo.png" width="40%" alt="Accord Project"/>
</div>

# Corda + Accord Project Template

This repo contains an MVP integration between Corda and the Accord Project. The goal of this application is to demonstrate how a contract may be parsed using [Accord Project Cicero](https://github.com/accordproject/cicero) and the issued as a Corda State onto the ledger.

This repository is divided into two parts: a Java reference solution (which is complete), and a Kotlin reference solutions (which is still a WIP). 

# Setup

### Tools 
* JDK 1.8 latest version
* IntelliJ latest version (2017.1 or newer)
* git
* Node.js v8.x (LTS)

After installing the required tools, clone or download a zip of this repository, and place it in your desired location.

### Cicero setup

* from the command line where you placed this repository, run: `npm install` (to install `cicero` and the `promissory-note` template)

### IntelliJ setup
* From the main menu, click `open` (not `import`!) then navigate to where you placed this repository.
* Click `File->Project Structure`, and set the `Project SDK` to be the JDK you downloaded (by clicking `new` and 
nagivating to where the JDK was installed). Click `Okay`.
* Next, click `import` on the `Import Gradle Project` popup, leaving all options as they are. 
* If you do not see the popup: Navigate back to `Project Structure->Modules`, clicking the `+ -> Import` button,
navigate to and select the repository folder, select `Gradle` from the next menu, and finally click `Okay`, 
again leaving all options as they are.

# Instructions
* In this repo, a Java object has been precompiled from Cicero based on the promissory note example.
* No additional setup is required. To parse a state and issue a Promissory note on the ledger see either `Running The Tests` or `Deploying Locally`.

### Running the tests
* Java: Select `Java - Unit tests` from the dropdown run configuration menu, and click the green play button.
* Note - there is currently only one test, which demonstrates the Corda-Accord integration. Other tests are a carry-over from the training material. They will be adapted to test this integration more thoroughly.
* Kotlin: N/A, still a WIP.
* Individual tests can be run by clicking the green arrow in the line number column next to each test.
* When running flow tests you must add the following to your run / debug configuration in the VM options field. This enables us to use
* Quasar - a library that provides high-performance, lightweight threads.
* "-javaagent: /PATH_TO_FILE_FROM_ROOT_DIR/quasar.jar"

### Deploying Locally
* From the root directory run './gradlew deployNodes' at the command line.
* From the root directory you may then run 'java-source/build/nodes/runNodes'

# Template Files

### Accord
* Promissory Note Template: `node_modules/promissory-note`
* Promissory Note Contract: `contract.txt`
* Promissory Note Java Object & Associated Classes: `java-source/src/main/java/org`
* Cicero-Parse Shell Script: `java-source/src/main/resources/cicero-parse.sh`

### Java
State:

* Template: `java-source/src/main/java/net/corda/training/state/PromissoryNoteState.java`
* Tests: `java-source/src/test/java/net/corda/training/state/PromissoryNoteStateTests.java`

Contract:

* Template: `java-source/src/main/java/net/corda/training/contract/PromissoryNoteContract.java`
* Issue Tests: `java-source/src/test/java/net/corda/training/contract/PromissoryNoteIssueTests.java`
* Transfer Tests: `java-source/src/test/java/net/corda/training/contract/PromissoryNoteIssueTests.java`
* Settle Tests: `java-source/src/test/java/net/corda/training/contract/PromissoryNoteIssueTests.java`

Flow:

* Issue template: `java-source/src/main/java/net/corda/training/flow/PromissoryNoteIssueFlow.java`
* Issue tests: `java-source/src/test/java/net/corda/training/flow/PromissoryNoteIssueFlowTests.java`
* Transfer template: `java-source/src/main/java/net/corda/training/flow/PromissoryNoteTransferFlow.java`
* Transfer tests: `java-source/src/test/java/net/corda/training/flow/PromissoryNoteTransferFlowTests.java`
* Settle template: `java-source/src/main/java/net/corda/training/flow/PromissoryNoteSettleFlow.java`
* Settle tests: `java-source/src/test/java/net/corda/training/flow/PromissoryNoteSettleFlowTests.java`
