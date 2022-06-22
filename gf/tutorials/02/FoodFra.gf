concrete FoodFra of Food = {
    lincat
        Comment, Item, Kind, Quality = Str;
    lin
        Pred item quality = item ++ "est" ++ quality;
        This kind = "ce" ++ kind;
        That kind = "ce" ++ kind;
        Mod quality kind = kind ++ quality;
        Wine = "vin";
        Cheese = "fromage";
        Fish = "poisson";
        Very quality = "très" ++ quality;
        Fresh = "frais";
        Warm = "tiède";
        Italian = "italien";
        Expensive = "dispendieux";
        Delicious = "délicieux";
        Boring = "ennuyant";

}
