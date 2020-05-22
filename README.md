# Information Retrieval - Spark Exercise

This is the base project for our **Information Retrieval - Spark Exercise**. 
In the following we cover the basic steps to get either [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Scala IDE](http://scala-ide.org/) running, and how to import this project in order to play with [Apache Spark](https://spark.apache.org/) right away. 

## Java SDK
We need a Java SDK to compile our code.
We recommend using OpenJDK.
You can get OpenJDK from [java.net](https://openjdk.java.net) or [AdoptOpenJDK](https://adoptopenjdk.net).
The latter provides convenient installers instead of plain archives.
We run our code with OpenJDK 13 but it should in principle work with any Java version that is compatible with Scala 2.12.
See [scala-lang.org](https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html) for more details on JDK compatibility.

## IntelliJ IDEA
Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

Install the Scala Plugin:

* Go to Preferences in IntelliJ
* Select Plugins
* Install the Scala Plugin (if it isn't already installed)

### Clone project
Next we clone the project which you will base your work on.

* File > New > Project from Version Control > Git
* Enter URL: https://bitbucket.org/dmir_wue/teaching-ir-spark-exercise.git
* Follow dialog
* Rename project `teaching-ir-spark-exercise` to `ir-spark-exercise`
* Right-click on the project
* Maven > Reimport

### Run Example
We prepared a small example for you to get started with Spark on your local machine.

* Right-click on "SimpleSparkExample" in the package de.uniwue.ir.example
* Run 'SimpleSparkExample'

Note: It's possible that this fails with `Error: scalac: bad option: '-make:transitive'`. See the FAQ section for how to fix it.

## Scala IDE
Download and install the [Scala IDE](http://scala-ide.org/).

### Clone project
Next we clone the project which you will base your work on.

* File > Import
* Select "Projects from Git"
* Select "Clone URI"
* Enter URI: https://bitbucket.org/dmir_wue/teaching-ir-spark-exercise.git
* Follow dialog
* Rename project `teaching-ir-spark-exercise` to `ir-spark-exercise`

### Run Example
We prepared a small example for you to get started with Spark on your local machine.

* Right-click on "SimpleSparkExample.scala"
* Run As... > Scala Application

## Contact
Feel free to contact me if you have trouble setting up your project, or to submit pull requests if you ideas on how to improve this base project.

* Michael Steininger, [steininger@informatik.uni-wuerzburg.de](mailto:steininger@informatik.uni-wuerzburg.de)

## FAQ

### java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.
If you are a Windows user, chances are high, that you will encounter the error:

    java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.

To solve this, add the following line as the first line to your "main" method (adjust the path according to you setup):

    System.setProperty("hadoop.home.dir", "C:\\<PATH TO THE WORKSPACE>\\teaching-ir-spark-exercise\\assets\\winutils\\<WINDOWS VERSION>");

### Error: scalac: bad option: '-make:transitive'
This bug can occur when using IntelliJ.
To fix it, open the file `scala_compiler.xml` in the `.idea` folder and delete the line `<parameter value="-make:transitive" />`.
You should be able to run Scala code thereafter.

### Scala IDE issues (memory, etc.)
If you run into problems with the Scala IDE, please have a look at their [FAQ](http://scala-ide.org/docs/current-user-doc/faq/index.html).
Among other things it shows how to increase the memory used by the Scala IDE which can improve performance.
