# Create a build of the application in the build folder
createStructure()
{
    mkdir bin
    mkdir bin/shapes
    mkdir bin/data
}

if [ -d ./bin ]
then
    echo "Path exists, removing existing source files, and creating again"
    rm bin/data/*
    echo "shapes"
    rm bin/shapes/*
    rm bin/*
    cd bin
    rmdir shapes
    rmdir data
    cd ..
    rmdir bin
    createStructure
else
    echo "Path does not exist, creating now"
    createStructure
fi

# Shapes
echo "Compiling classes regarding shapes: e.g. Circle, PlanarShape, Polygon, Point, etc"
cd src/shapes
javac -s ../../bin -d ../../bin *.java

# Data
echo "Compiling classes regarding data: e.g. Node, Linkedlist, etc"
cd ../data
cp *.java ../../bin/data
cd ../../bin/
javac data/*.java
rm data/*.java

# Everything else
echo "Compiling enums and main classes"
cd ../src
cp *.java ../bin
cd ../bin
javac *.java
rm *.java

# Copy any text files over
cd ../src
cp *.txt ../bin

echo "Compilation is now complete, run progam with 'java bin/PA2a test.txt' or 'java bin/PA2b b.txt'"