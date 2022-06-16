package ca.ntro.cards.arraylist.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.arraylist.ArraylistConstants;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class ArraylistProcedureWorld2d extends ProcedureWorld2d<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> {
    
    private ProcedureMsgAcceptManualModel msgManualExecutionStep = NtroApp.newMessage(ProcedureMsgAcceptManualModel.class);
   
    private ArraylistCard2d lastFlippedCard = null;
    
    private ArraylistCard2d draggedCard = null;
	private double draggedCardOriginalTopLeftX;
	private double draggedCardOriginalTopLeftY;

	@Override
	public void registerDraggedObject2d(ProcedureObject2d object2d) {
		if(object2d instanceof ArraylistCard2d) {
			draggedCard = (ArraylistCard2d) object2d;
			draggedCardOriginalTopLeftX = draggedCard.getTopLeftX();
			draggedCardOriginalTopLeftY = draggedCard.getTopLeftY();
		}

		super.registerDraggedObject2d(object2d);
	}
	public void registerFlippedCard(ArraylistCard2d card2d) {
		this.lastFlippedCard = card2d;
	}
    @Override
    public void buildAndSendManualModel() {

        // TODO: analyser les cartes2d existantes et créer un CardsModel 
        
        ListeTableau manualModel = new ListeTableau();
        
        List<ArraylistCard2d> cards =collectCardsExceptDraggedCard();
        
        /*
        if(draggedCard!=null) {
        	addNullCardAtDraggedCardOriginalLocation();
        }
        */
        
        msgManualExecutionStep.setManualModel(manualModel);
        msgManualExecutionStep.send();
    }

    private void addNullCardAtDraggedCardOriginalLocation(List<ArraylistCard2d> cards) {
    	ArraylistCard2d draggedCardLocation = new ArraylistCard2d(new NullCard());
		draggedCardLocation.setTopLeftX(draggedCardOriginalTopLeftX);
		draggedCardLocation.setTopLeftY(draggedCardOriginalTopLeftY);

		cards.add(draggedCardLocation);
	}
    private List<ArraylistCard2d> collectCardsExceptDraggedCard(){
		List<ArraylistCard2d> cards = new ArrayList<>();

		for(Object2d object2d : getObjects()) {
			if(object2d instanceof ArraylistCard2d
					&& object2d != draggedCard) {
				cards.add((ArraylistCard2d) object2d);
			}
		}
		
		return cards;
	}
}
