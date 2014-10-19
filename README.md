# BPMNviz [![Build Status](https://travis-ci.org/uniba-dsg/BPMNviz.png?branch=master)](https://travis-ci.org/uniba-dsg/BPMNviz)

BPMNviz creates for each found `.bpmn` or `.bpmn2` file image siblings at `.bpmn.png` or `.bpmn2.png` via the [Yaoqiang BPMN Editor](http://bpmn.sourceforge.net/) automatically .

## License

The license is GPLv3, as the Yaoqiang-BPMN-Editor-2.2.18 is also GPLv3.

## Installation

Requires JDK 8 with JAVA_HOME set to the JDK path!

    $ git clone git@github.com:uniba-dsg/BPMNviz
    $ cd BPMNviz
    $ gradlew installApp
    # add BPMNviz/build/install/BPMNviz/bin to PATH

## Usage

    $ BPMNviz some/folder # for each .bpmn or .bpmn2 file create a .bpmn.png or .bpmn2.png file

## Authors

[Simon Harrer](mailto:simon.harrer@gmail.com)

## Contribute

    10 Fork
    20 Create feature branch
    30 Create commits
    40 Create pull request
    GOTO 10
