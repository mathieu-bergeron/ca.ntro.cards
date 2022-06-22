resource ResFra = {

    param

        Gender = Masc | Fem ;
        Number = Sg | Pl ;

    oper

        Phrase : Type = { s: Str };

        NounPhrase : Type = { s: Str ; g : Gender ; n : Number };

        Noun : Type = { s: Number => Str; g : Gender };

        Adj :  Type = { s: Gender => Number => Str };

        det : (n:Number) -> (masc:Str) -> (fem:Str) -> (noun:Noun) -> NounPhrase

            = \n,masc,fem,noun -> 

                { s = table { Masc => masc ; Fem => fem } ! noun.g ++ noun.s ! n ; g = noun.g; n = n } ;

        noun : Gender -> (man:Str) -> (men:Str) -> { s: Number => Str ; g : Gender }
         
             = \gender,man,men -> { s = table { Sg => man ; Pl => men } ; g = gender };

        regNoun : Gender -> Str -> { s: Number => Str; g : Gender }

                = \gender,car -> noun gender car (car + "s") ;

        adj : (mascSg, mascPl, femSg, femPl : Str) -> Adj

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

        adjPhrase : (a:Str) -> (adj:Adj) -> Adj

               = \a,adj -> { s = table {
                                      Masc => table { Sg => a ++ adj.s ! Masc ! Sg; Pl => a ++ adj.s ! Masc ! Pl};
                                      Fem => table { Sg => a ++ adj.s ! Fem ! Sg ; Pl => a ++ adj.s ! Fem ! Pl }
                                  }
                            } ;
}

