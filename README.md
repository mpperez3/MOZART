# Compound enzyme interaction predictor - MOZART 

Develop models able to predict interactions between drugs and enzymes is a major goal in computational biology since these models may be used for both, predict new active drugs as well as predict the interaction between known drugs on untested targets. Due to this, the main aim of this work was to develop a novel multi-target machine learning (MTML) quantitative structure activity relationship (QSAR) model to predict the interaction between drugs and enzyme targets. The model was developed using features of both, drugs and enzyme while the ML used was the artificial neural network (ANN) multi-layer perceptron (MLP). The total drug-enzyme pairs used to develop this model is 66829. Validation of the model was performed using cross-validation, Mathews correlation coefficient, ROC curve and other relevant statistics. Overall accuracy of the presented model is higher than 93%. To maximize the diffusion of this model, a public accessible Web server has been established at, by which users can make their own prediction. 

The main aim of MOZART (coMpOund enZyme interAction pRedicTor) is to identify the interaction between a compound and enzyme subclasses targets. The model implanted in the platform was developed using a multi-target machine learning model based on an artificial neural network multi-layer perceptron algorithm.

The presented model was able to predict enzymatic reactions of a query molecule with a high accuracy. The input data is codified in the SMILE specification and the model is be able to predict the Enzyme Commission number (EC number) that the reactions can catalyze.

## Run
Download and compile the Java code or download the runnable JAR from [here](https://osf.io/jgyfq/?view_only=87b6de3a15f34250b4595c8832c490b7)

- Make sure Java 7 or higher is installed
Run the following command to confirm:

```bash
$ java -version
java version "1.7.0_51"
Java(TM) SE Runtime Environment (build 1.7.0_51-b13)
Java HotSpot(TM) 64-Bit Server VM (build 24.51-b03, mixed mode)
```
-  Run the MOZART-1.0-SNAPSHOT.jar

```bash
$ java -jar MOZART-1.0-SNAPSHOT.jar
```
- Remove the chemSmile.db.mv.db to update to a new version of MOZART

## LICENSE
[![License](https://img.shields.io/badge/License-BSD%203--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)