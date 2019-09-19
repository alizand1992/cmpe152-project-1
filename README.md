# Computer Engineering 152 - Compiler Design - Project 1
> This is the first team project for Compiler Design course

## Description

## Setup
* TESTED ON UBUNTU ONLY
* Preferred IDE:
  * Intellij IDEA
* Install Java 11

### Method 1 (Using IDE):
Through GitHub:
1. File > New > Project From Version Control > Git
2. Enter `git@github.com:alizand1992/cmpe152-project-1.git` for URL.
3. Select a directory for the project.

or 

1. Download Zip file and extract in a desired location.
2. File > New > Project Form Existing Sources
3. Select the directory where the source code is unzipped.

Common Steps:
1. File > Project Structure > Project > Project SDK:
   - This needs to be set to 11

To Run Tests:
* Go to a desired test file and click on the triangle next to the class to run all test or triangle next to individual test.

To Run Application:
* Run/Debug Configuration 
* Add new `Application` type configuration
* Three dots next to `Main Class`
  * Select `Compiler`
  * Name the configuration to anything
  * Run or Debug as desired.
 
### Method 2 (Using Terrminal):
For this method be sure to have openjdk installed:
* `sudo apt install openjdk-11-jdk-headless` (headless to save space)
* `sudo apt install openjdk-11-jdk` (if you might use GUI in the future)
1. download or clone as suggested in `Mehtod 1` above.
2. navigate to the directory in terminal
3. `javac src/main/java/lexer/*.java src/main/java/Compiler.java` to compile
4. `java -cp src/main/java Compiler` to run
5. provide a file name.

#### Sample output:

```text
Please enter a file to compile: 
Working Directory: /home/ali/sjsu/cmpe152-project-1
sample_code
{		{
BASE_TYPE	int
ID		b
;		;
ID		b
=		=
NUM		1
;		;
{		{
BASE_TYPE	int
ID		a
;		;
ID		a
=		=
NUM		2
;		;
DO		do
ID		a
=		=
ID		a
+		+
NUM		1
;		;
WHILE		while
(		(
ID		a
<		<
NUM		100
)		)
;		;
}		}
}		}
```

Running tests:
For this method be sure to have Maven installed:
* `sudo apt install maven`
* Navigate to the project directory
* `mvn test`

#### Sample Test Result
```-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running HelloWorldTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.027 sec
Running lexer.LexerTest
Tests run: 22, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.023 sec
Running lexer.TokenTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec

Results :

Tests run: 24, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.654 s
[INFO] Finished at: 2019-09-18T21:01:30-07:00
[INFO] ------------------------------------------------------------------------
```

## Authors:
* [lucy chibukhchyan](https://github.com/lucyc426)
* [Lorena Silva](https://github.com/lorena9s)
* [Ali Zand](https://github.com/alizand1992)
* [Andrew Kosche](https://github.com/Andrewkosche123)
