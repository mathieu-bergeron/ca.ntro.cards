concrete FoodEng of Food = {

    {- table types

     Number => Str                               -- type
     table {Sg => "fish" ; Pl => "fishes" }      -- value
     table {Sg => "fish" ; Pl => "fishes" } ! Sg -- selecting Sg

     Gender => Number => Str

     table {

        Masc => table {Sg => "beau"; Pl => "beaux"};
        Fem  => table {Sg => "belle"; Pl => "belles"}
     }

     table {

        _ => table {Sg => "acerbe"; Pl => "acerbes"};  -- pas de différence de genre
     }


    record

    { s: Str; n: Number }     -- type
    {s = "these"; n = Pl}     -- value
    {s = "these"; n = Pl}.s   -- selecting s



    -}

    param

       Number = Sg | Pl;

    lincat
        
        Comment = { s : Str } ;
        Item    = { s: Str ; n : Number } ;
        Kind    = { s: Number => Str } ;
        Quality = { s: Str };

    oper

        regNoun : Str -> { s: Number => Str } ;

        -- + is the gluing operator, makes a single token
        regNoun = \word -> { s = table {Sg => word ; Pl => word + "s"}};



    lin

        {-

        les fonctions concrètes 

        -}

        With kind1 kind2 = kind1 ++ "with" ++ kind2;

        Pron r = "it";

        Pred item quality = 
            { 
                s = item.s 
                    ++ table { Sg => "is" ; Pl => "are" } ! item.n
                    ++ qual.s
            } 

        This kind = "this" ++ kind;

        That kind = "that" ++ kind;

        Mod quality kind = quality ++ kind;

        Wine = { s = table { Sg => "wine" ; Pl => "wines" } } ;

        Cheese = "cheese";

        Fish = "fish";

        Very quality = "very" ++ quality;

        Fresh = "fresh";

        Warm = "warm";

        Italian = "Italian";

        Expensive = "exepensive";

        Delicious = "delicious" | "exquisite" | "tasty" ;

        Boring = { s: "boring" } ;




}
