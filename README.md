# start description
## start from file
### steps
1. enter into the project directory
2. run: mvn install
3. run: mvn package
4. run: cd target
5. java -jar test_train-1.0-SNAPSHOT.jar file /Users/hzhu2/Documents/workspace/test_train/input.txt

### java parameters
1. file --- the IOType name, will decide which ways to read the input data
2. /Users/hzhu2/Documents/workspace/test_train/input.txt --- input file url

---

## start from java params
### steps
1. enter into the project directory
2. run: mvn install
3. run: mvn package
4. run: cd target
5. run: java -jar test_train-1.0-SNAPSHOT.jar argString AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7

### java parameters
1. argString --- the IOType name, will decide which ways to read the input data
2. AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7 --- input data (notice: no any space in the string)

---

# design description
