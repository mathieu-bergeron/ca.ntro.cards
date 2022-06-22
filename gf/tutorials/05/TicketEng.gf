concrete TicketEng of Ticket = {

    lincat

        Phrase, Place = Str;

    lin

        GetTicket p1 p2 = 

            (

                (

                    (
                        "I" ++ ("would like" | "want") ++ "to get"

                        | ("may" | "can" ) ++ "I get"

                        | "can you give me"

                        | []

                    ) 

                ++ "a ticket"

                ) 

            | []

            )

            ++ "from" ++ p1 ++ "to" ++ p2 ++

            ("please" | []) ;


        Montreal = "Montréal";

        Quebec = "Québec";


}
