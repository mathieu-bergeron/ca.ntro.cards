concrete FoodEng of Food = {

    lincat

        -- les catégories concrètes
        Comment, Item, Kind, Quality = Str;

    lin

        {-

        les fonctions concrètes 

        -}

        Pred item quality = item ++ "is" ++ quality;
        This kind = "this" ++ kind;
        That kind = "that" ++ kind;
        Mod quality kind = quality ++ kind;
        Wine = "wine";
        Cheese = "cheese";
        Fish = "fish";
        Very quality = "very" ++ quality;
        Fresh = "fresh";
        Warm = "warm";
        Italian = "Italian";
        Expensive = "exepensive";
        Delicious = "delicious";
        Boring = "boring";


}
