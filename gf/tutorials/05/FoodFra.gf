concrete FoodFra of Food = {
    lincat

        Comment, Item, Kind, Quality = Str;

    lin

        With kind1 kind2 = kind1 ++ "avec" ++ kind2;

        {- oups, le pronom est plus compliqué en français -}
        Pron r = "il";

        Pred item quality = item ++ "est" ++ quality;
        This kind = "ce" ++ kind;
        That kind = "ce" ++ kind;



        {- ordre inverse d'en anglais -}
        Mod quality kind = kind ++ quality;

        Wine = "vin";
        Cheese = "fromage";
        Fish = "poisson";
        Very quality = "très" ++ quality;
        Fresh = "frais";
        Warm = "tiède";
        Italian = "italien";
        Expensive = "dispendieux";
        Delicious = "délicieux" | "goûteux" | "exquis" ;
        Boring = "ennuyant";

}
