
git clone git@github.com:GrammaticalFramework/gf-core.git
cd src/runtime/c/
setup.sh configure
setup.sh build
sudo setup.sh install

cd ../java
vim Makefile

    JNI_INCLUDES  = -I /usr/lib/jvm/java-11-openjdk/include -I /usr/lib/jvm/java-11-openjdk/include/linux

make
sudo make install

# re-configurer les librairies partagées

$ export LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
$ sudo ldconfig

# tester
$ javac Test.java
$ java Test





