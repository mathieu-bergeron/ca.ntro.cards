--# -path=.:/home/mbergeron/.local/gf-rgl/present

incomplete concrete FoodI of Food =
    open Syntax,LexFood in {

    lincat

        Comment = Utt ;
        Item = NP ;
        Kind = CN ;
        Quality = AP ;

    lin

        Pred item quality = mkUtt (mkCl item quality) ;

        This kind = mkNP this_Det kind ;
        That kind = mkNP that_Det kind ;
        These kind = mkNP these_Det kind ;
        Those kind = mkNP those_Det kind ;

        Mod quality kind = mkCN quality kind ;

        Wine = mkCN wine_N ;
        Pizza = mkCN pizza_N ;
        Cheese = mkCN cheese_N ;
        Fish = mkCN fish_N ;

        Very quality = mkAP very_AdA quality ;

        Fresh = mkAP fresh_A ;
        Warm = mkAP warm_A ;
        Italian = mkAP italian_A ;
        Expensive = mkAP expensive_A ;
        Delicious = mkAP delicious_A ;
        Boring = mkAP boring_A ;

}
