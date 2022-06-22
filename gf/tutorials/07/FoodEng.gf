concrete FoodEng of Food = {

    param

        Number = Sg | Pl ;

    lincat

        Comment, Quality = { s: Str };

        Kind = { s: Number => Str };

        Item = { s: Str ; n : Number };

    oper

        det : Number -> Str -> { s : Number => Str} -> { s : Str ; n : Number }

            = \n,det,noun -> { s = det ++ noun.s ! n ; n = n } ;

        noun : Str -> Str -> { s: Number => Str }
         
             = \man,men -> { s = table { Sg => man ; Pl => men } };

        regNoun : Str -> { s: Number => Str }

                = \car -> noun car (car + "s") ;

        adj : Str -> { s : Str }

            = \cold -> { s = cold };

        copula : Number => Str

               = table { Sg => "is" ; Pl => "are" };

    lin

        Pred item quality = { s = item.s ++ copula ! item.n ++ quality.s };

        This = det Sg "this";
        That = det Sg "that";
        These = det Pl "these";
        Those = det Pl "those";

        Mod quality kind = { s = \\n => quality.s ++ kind.s ! n } ;

        Wine = regNoun "wine";
        Cheese = regNoun "cheese";
        Fish = noun "fish" "fish";
        Pizza = regNoun "pizza";

        Very quality = { s = "very" ++ quality.s };

        Fresh = adj "fresh";
        Warm = adj "warm";
        Italian = adj "Italian";
        Expensive = adj "expensive";
        Delicious = adj "delicious";
        Boring = adj "boring";




}
