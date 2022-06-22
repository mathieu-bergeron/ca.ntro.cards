concrete FoodEng of Food = {

    lincat

        -- les catégories concrètes
        Comment, Item, Kind, Quality, ComplexKind = Str;

    lin

        {-

        les fonctions concrètes 

        -}

        With kind1 kind2 = kind1 ++ "with" ++ kind2;
        Pron r = "it";
        Pred item quality = item ++ "is" ++ quality;
        This kind = "this" ++ kind;
        That kind = "that" ++ kind;
        ThisComplex complexKind = "this" ++ complexKind;
        ThatComplex complexKind = "that" ++ complexKind;
        Mod quality kind = quality ++ kind;
        Wine = "wine";
        Cheese = "cheese";
        Fish = "fish";
        FishCheese = "fish with cheese";
        Very quality = "very" ++ quality;
        Fresh = "fresh";
        Warm = "warm";
        Italian = "Italian";
        Expensive = "exepensive";
        Delicious = "delicious" | "exquisite" | "tasty" ;
        Boring = "boring";


}
