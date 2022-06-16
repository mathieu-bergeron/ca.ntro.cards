package ca.ntro.cards.arraylist.models;


import ca.ntro.cards.common.commands.AddCommand;
import ca.ntro.cards.common.commands.Command;
import ca.ntro.cards.common.commands.DeleteCommand;
import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.arraylist.frontend.ArraylistProcedureViewData;
import ca.ntro.cards.arraylist.frontend.views.ArraylistVariablesView;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureObject2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.values.ComparisonReport;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class   ListeTableau<C extends Comparable<C>> 

       extends ProcedureCardsModel<ListeTableau, 
                                   ArraylistProcedureObject2d, 
                                   ArraylistProcedureWorld2d, 
                                   ArraylistProcedureDrawingOptions, 
                                   ArraylistProcedureViewData,
                                   ArraylistVariablesView> { 
    
    private List<Command<C>> commands = new ArrayList<>();
    
    private C lastGet = null;
    
    protected C[] grandTableau = (C[]) new Card[1];
    protected C[] nouveauGrandTableau = null;

    protected int indicePremierElement = 0;
    protected int indiceDernierElement = 0;

    public C[] getGrandTableau() {
        return grandTableau;
    }

    public void setGrandTableau(C[] grandTableau) {
        this.grandTableau = grandTableau;
    }

    public int getIndicePremierElement() {
        return indicePremierElement;
    }

    public void setIndicePremierElement(int indicePremierElement) {
        this.indicePremierElement = indicePremierElement;
    }

    public int getIndiceDernierElement() {
        return indiceDernierElement;
    }

    public void setIndiceDernierElement(int indiceDernierElement) {
        this.indiceDernierElement = indiceDernierElement;
    }

    public List<Command<C>> getCommands() {
        return commands;
    }

    public void setCommands(List<Command<C>> commands) {
        this.commands = commands;
    }

    public C getLastGet() {
        return lastGet;
    }

    public void setLastGet(C lastGet) {
        this.lastGet = lastGet;
    }

    @Override
    public void copyDataFrom(ListeTableau other) {
        // TODO: copier les données telles quelles

    }

    @Override
    public boolean isValidNextStep(ListeTableau manualModel) {
        //retourne vrai si manualModel est une prochaine �tape de l'ex�cution valide
        //retourne faux si ce n'est pas une prochaine �tape valide de l'ex�cution
        boolean modified = false;
        if(manualModel.getGrandTableau().length!=grandTableau.length) {
            modified=true;
            copyDataFrom(manualModel);
        }
        /*cr�er une m�thode qui trouve s'il y a une carte qui n'est pas null dans le this et qui 
         * devient nul avec le manualModel. Il faut v�rifier si l'indice de la carte correspond  avec
         * le testUnitaire. Sinon on le rejette.
        */
        if(Math.abs(this.indicePremierElement-manualModel.indicePremierElement)>1) {
            modified = false;
        }
        if(Math.abs(this.indiceDernierElement-manualModel.indiceDernierElement)>1) {
            modified = false;
        }
        /*Cas compliqu�
         * this=valeur courante/�tape courante de l'ex�cution,
         *ManualModel est la prochaine �tape de l'ex�cution
         *On veut accepter en comparant this � manual model et on v�rifie si la transition est valide
         */

        return modified;
    }

    @Override
    public ComparisonReport compareToSolution(ListeTableau solution) {
        ComparisonReport report = ComparisonReport.emptyReport();
        return report;
    }

    @Override
    protected void updateViewDataImpl(ArraylistProcedureViewData cardsViewData) {
        // TODO: créer des Carte2d pour afficher les cartes du modèle
    }

    @Override
    public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
        if(descriptor.testCaseId().equals("ex01")) {
            
            grandTableau = (C[]) new Card[]{null, 
                                           null,
                                           new Card(1, Suit.HEARTS),
                                           new Card(3, Suit.CLUBS),
                                           new Card(5, Suit.SPADES),
                                           new Card(8, Suit.DIAMONDS),
                                           new Card(2, Suit.DIAMONDS),
                                           null,
                                           null};
            
            indicePremierElement = 2;
            indiceDernierElement = 6;
            
            commands.add(new DeleteCommand(4));

        }

        else if(descriptor.testCaseId().equals("ex02")) {
            
            grandTableau = (C[]) new Card[]{null, 
                                           null,
                                           new Card(6, Suit.DIAMONDS),
                                           new Card(2, Suit.CLUBS),
                                           new Card(4, Suit.HEARTS),
                                           new Card(7, Suit.HEARTS),
                                           new Card(3, Suit.DIAMONDS),
                                           new Card(1, Suit.SPADES),
                                           null,
                                           null};
            
            indicePremierElement = 2;
            indiceDernierElement = 7;
            
            commands.add(new DeleteCommand(1));

        }

        // TODO: créer les autres cas de test
    }

    @Override
    public int testCaseSize() {
        // TODO: 
        return commands.size();
    }
    
    @Override
    protected Stream<Card> cards() {
        return new StreamNtro<Card>() {
            @Override
            public void forEach_(Visitor<Card> visitor) throws Throwable {
                if(grandTableau != null) {
                    for(C card : grandTableau) {
                        visitor.visit((Card) card); 
=======
    protected void updateViewDataImpl(ArraylistProcedureViewData cardsViewData) {
        double cardWidth =ArraylistConstants.INITIAL_CARD_WIDTH_MILIMETERS;
        double cardHeight = ArraylistConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

        for(int i = 0; i < grandTableau.length; i++) {

            double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
            double targetTopLeftY = cardHeight * 2;
            
            AbstractCard card = (Card) grandTableau[i];
            
            if(card == null) {
                card = new NullCard();
            }
            
            cardsViewData.addOrUpdateCard(card,
                                          targetTopLeftX,
                                          targetTopLeftY);

            cardsViewData.displayCardFaceUp(card);
        }   

        double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePremierElement() * cardWidth * 3 / 2;
        double markerTopLeftY = cardHeight * 3 + cardHeight / 3;
        
        cardsViewData.addOrUpdateMarker("indexOfFirstElement", "red", markerTopLeftX, markerTopLeftY);

        markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndiceDernierElement() * cardWidth * 3 / 2;
        markerTopLeftY = cardHeight * 3 + cardHeight / 3;
        
        cardsViewData.addOrUpdateMarker("indexOfLastElement", "blue", markerTopLeftX, markerTopLeftY);
        
        
    
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
        if (descriptor.testCaseId().equals("ex01")) {

            grandTableau = (C[]) new Card[] { 
                    null, null,
                    new Card(1, Suit.HEARTS), new Card(3, Suit.CLUBS),
                    new Card(5, Suit.SPADES), new Card(8, Suit.DIAMONDS),
                    new Card(2, Suit.DIAMONDS),
                    null, null };

            indicePremierElement = 2;
            indiceDernierElement = 6;

            commands.add(new DeleteCommand(4));

        }

        else if (descriptor.testCaseId().equals("ex02")) {

            grandTableau = (C[]) new Card[] { null, null,
                    new Card(6, Suit.DIAMONDS), new Card(2, Suit.CLUBS),
                    new Card(4, Suit.HEARTS), new Card(7, Suit.HEARTS), 
                    new Card(3, Suit.DIAMONDS), new Card(1, Suit.SPADES),
                    null, null };

            indicePremierElement = 2;
            indiceDernierElement = 7;

            commands.add(new DeleteCommand(1));

        }
    }

    @Override
    public int testCaseSize() {
        // TODO:
        //return commands.size();
        return grandTableau.length;
    }

    @Override
    protected Stream<Card> cards() {
        return new StreamNtro<Card>() {
            @Override
            public void forEach_(Visitor<Card> visitor) throws Throwable {
                if (grandTableau != null) {
                    for (C card : grandTableau) {
                        visitor.visit((Card) card);
>>>>>>> d53e96c... ajout) couleur pour le Marker2d
                    }
                }

                if(nouveauGrandTableau != null) {
                    for(C card : nouveauGrandTableau) {
                        visitor.visit((Card) card); 
                    }
                }
            }
        };
    }

    
    @Override
    public void run() {
        while(!commands.isEmpty()) {
            Command<C> command = commands.get(0);
            commands = commands.subList(1, commands.size());

            if(command.isAdd()) {

                ajouter(command.add().getValue());

            }else if(command.isGet()) {
                
                lastGet = obtenir(command.get().index());
                
            }else if(command.isDelete()) {
                
                retirer(command.delete().index());
                
            }else if(command.isInsert()) {
                
                inserer(command.insert().index(), command.insert().getValue());

            }
        }
        
    }


    public void ajouter(C valeur) {
    }

    public C obtenir(int index) {
        return null;
    }

    public void retirer(int index) {
    }

    public void inserer(int index, C valeur) {
    }
    
    protected C[] creerGrandTableau(int size) {
        return (C[]) new Card[size];
    }

    @Override
    public void displayOn(ArraylistVariablesView variablesView) {
        // TODO: afficher les attributs
    }



}
