concrete FoodFra of Food = {

    param

        Gender = Masc | Fem ;
        Number = Sg | Pl ;

    lincat

        Comment = { s: Str };

        Quality = { s: Gender => Number => Str } ;

        Kind = { s: Number => Str; g : Gender };

        Item = { s: Str ; g : Gender; n : Number };

    oper

        det : Number -> Str -> Str -> { s : Number => Str ; g : Gender} -> { s : Str ; g : Gender ; n : Number }

            = \n,masc,fem,noun -> 

                { s = table { Masc => masc ; Fem => fem } ! noun.g ++ noun.s ! n ; g = noun.g; n = n } ;

        noun : Gender -> Str -> Str -> { s: Number => Str ; g : Gender }
         
             = \gender,man,men -> { s = table { Sg => man ; Pl => men } ; g = gender };

        regNoun : Gender -> Str -> { s: Number => Str; g : Gender }

                = \gender,car -> noun gender car (car + "s") ;

        adj : Str -> Str -> Str -> Str -> { s: Gender => Number => Str }

            = \mascSg,mascPl,femSg,femPl -> 

                { s = table {
                        Masc => table { Sg => mascSg ; Pl => mascPl } ;
                        Fem => table { Sg => femSg ; Pl => femPl } 
                    };
                 };

        regAdj : Str -> Str -> { s : Gender => Number => Str }

            = \masc,fem -> adj masc (masc + "s") fem (fem + "s") ;

        copula : Number => Str

               = table { Sg => "est" ; Pl => "sont" } ;

        prepend : Str -> { s: Gender => Number => Str} -> { s: Gender => Number => Str }

               = \a,t -> { s = table {
                                      Masc => table { Sg => a ++ t.s ! Masc ! Sg; Pl => a ++ t.s ! Masc ! Pl};
                                      Fem => table { Sg => a ++ t.s ! Fem ! Sg ; Pl => a ++ t.s ! Fem ! Pl }
                                  }
                            } ;

    lin

        Pred item quality = { s = item.s ++ copula ! item.n ++ quality.s ! item.g ! item.n };

        This kind = det Sg "ce" "cette" kind ;          -- explicit parameter kind
        That = This ;
        These  = det Pl "ces" "ces"  ;                  -- currying, this returns a function taking Kind -> Item
        Those = These ;

        Mod quality kind = { s = quality.s ! kind.g; g = kind.g } ;

        Wine = regNoun Masc "vin";
        Cheese = regNoun Masc "fromage";
        Fish = regNoun Masc "poisson";
        Pizza = regNoun Fem "pizza";

        Very quality = prepend "très" quality;

        Fresh = regAdj "frais" "fraîche";
        Warm = regAdj "tiède" "tiède";
        Italian = regAdj "italien" "italienne";
        Expensive = adj "dispendieux" "dispendieux" "dispendieuse" "dispendieuses";
        Delicious = adj "délicieux" "délicieux" "délicieuse" "délicieuses";
        Boring = regAdj "ennuyant" "ennuyante";


}
