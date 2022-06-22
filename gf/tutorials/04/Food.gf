abstract Food = {

    flags 

        startcat = Comment;

    cat
        Comment ; Item ; Kind ; Quality ; ComplexKind ;

    fun
        With: Kind -> ComplexKind -> ComplexKind;
        Pron : Item -> Item;
        Pred: Item -> Quality -> Comment;
        This, That: Kind -> Item;
        ThisComplex, ThatComplex: ComplexKind -> Item;
        Mod: Quality -> Kind -> Kind;
        Wine,Cheese,Fish: Kind;
        FishCheese: ComplexKind;
        Very : Quality -> Quality;
        Fresh, Warm, Italian, Expensive, Delicious, Boring: Quality;


}
