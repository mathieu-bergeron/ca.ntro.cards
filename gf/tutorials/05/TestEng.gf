concrete TestEng of Test = {

    param

        Number = Sg | Pl ;

    lincat

        Phrase = Str;

        Place = { s: Number => Str } ;

    lin

        GetTicket p1 p2 = "from" ++ p1.s!Sg ++ "to" ++ p2.s!Sg;

        Montreal = { s = table { Sg => "Montréal" ; Pl => "Montréal" } };

        Quebec = { s = table { Sg => "Québec" ; Pl => "Québec" } };


}
