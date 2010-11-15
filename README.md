Java Unit Evolution
===================

Evolve programs using unit testing and genetic programming.

This code was produced as part of my Bachelor thesis at
[The Open University](http://www.open.ac.uk). Despite the promising
description, it is not possible to evolve anything but the most
trivial programs. Java Unit Evolution is of no practical use in its
current state.

Please read
[the paper](https://github.com/downloads/fhd/java-unit-evolution/java_unit_evolution.pdf)
to learn more.

Building
--------

To build Java Unit Evolution, get [Apache Maven](http://maven.apache.org/) and
execute:

	mvn package
	
Usage
-----

The work flow is as follows:

1. Specify the desired method as an abstract method of an abstract class.
2. Specify all possible operations as non-abstract methods of the same class.
3. Write JUnit test cases against the abstract method.
4. Run the JUnit test cases.

In the test case, instantiate the abstract class as follows:

    private ClassToEvolve cte = JavaUnitEvolution.evolve(ClassToEvolve.class,
                                                         getClass());

There are a few examples in the
*com.github.fhd.javaunitevolution.samples* package.

License
-------

Copyright (C) 2010 Felix H. Dahlke

This library is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as
published by the Free Software Foundation; either version 2.1 of the
License, or (at your option) any later version.

This library is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; see the file COPYING. If not, write
to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
Floor, Boston, MA 02110-1301 USA
