(echo import Food.gf
echo import FoodEng.gf
echo import FoodFra.gf
echo parse \"this very Italian wine is very expensive\" | lin -lang=Fra | wf
while read i; do
    if [ "$i" = "quit" -o "$i" = "exit" ]; then
        echo quit
        break;
    fi
    echo $i;
done) | gf

