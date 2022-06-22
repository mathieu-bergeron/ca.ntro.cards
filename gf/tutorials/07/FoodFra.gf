concrete FoodFra of Food = open ResFra in {

    lincat

        Comment = Phrase;

        Quality = Adj;

        Kind = Noun;

        Item = NounPhrase;

    lin

        Pred item quality = { s = item.s ++ copula ! item.n ++ quality.s ! item.g ! item.n };

        This kind = det Sg "ce" "cette" kind ;          -- explicit parameter kind
        That = This ;
        These  = det Pl "ces" "ces"  ;                  -- currying, this returns a function taking Kind -> Item
        Those = These ;

        Mod quality kind = 
            { s = table {
                        Sg => kind.s ! Sg ++ quality.s ! kind.g ! Sg ;
                        Pl => kind.s ! Pl ++ quality.s ! kind.g ! Pl 
                    }; 

              g = kind.g } ;

        Wine = regNoun Masc "vin";
        Cheese = regNoun Masc "fromage";
        Fish = regNoun Masc "poisson";
        Pizza = regNoun Fem "pizza";

        Very quality = adjPhrase "très" quality;

        Fresh = adj "frais" "frais" "fraîche" "fraîches";
        Warm = regAdj "tiède" "tiède";
        Italian = regAdj "italien" "italienne";
        Expensive = adj "dispendieux" "dispendieux" "dispendieuse" "dispendieuses";
        Delicious = adj "délicieux" "délicieux" "délicieuse" "délicieuses";
        Boring = regAdj "ennuyant" "ennuyante";


}
