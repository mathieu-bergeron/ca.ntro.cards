package ca.ntro.cards.arraylist.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.arraylist.ArraylistConstants;
import ca.ntro.cards.arraylist.messages.ArraylistMsgAcceptManualModel;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

//Copyright (C) (2022) (Marlond Augustin) (marlondjra@gmail.com)
//
//This file is part of Ntro
//
//This is free software: you can redistribute it and/or modify
//it under the terms of the GNU GPL3 General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//GNU GPL3 General Public License for more details.
//
//You should have received a copy of the GNU GPL3 General Public License
//along with aquiletour. If not, see <https://www.gnu.org/licenses/>
//cette méthode prend en note les différences entre les deux tableaux

public class ArraylistProcedureWorld2d extends ProcedureWorld2d<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> {
    
    private ArraylistMsgAcceptManualModel msgManualExecutionStep = NtroApp.newMessage(ArraylistMsgAcceptManualModel.class);
   
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
        
        List<ArraylistCard2d> cards =collectCards();
        
        if(draggedCard!=null&&draggedCard.collidesWith(0, 0, 100, 100)) {
        	addNullCardAtDraggedCardOriginalLocation(cards);
        	cards.remove(draggedCard);
        	//insertDraggedCardAtFinalLocation(cards);
        	forgetDraggedCard();
        }
        
        double cardWidth=ArraylistConstants.INITIAL_CARD_WIDTH_MILIMETERS;
        Object2d marker2d=objectById("smallestElement");
        Object2d marker2dPlusGrand=objectById("biggestElement");
        int indexOfSmallest=(int) Math.round((marker2d.topLeftX()-10-cardWidth-cardWidth/2)*2/3/cardWidth);
        int indexOfBiggest=(int) Math.round((marker2dPlusGrand.topLeftX()-10-cardWidth-cardWidth/2)*2/3/cardWidth);
        List<ArraylistCard2d> grandTableau2d =new ArrayList<>();
        grandTableau2d.addAll(cards);
        orderCardsLeftToRight(grandTableau2d);
                
        Card[] newGrandTableau = new Card[grandTableau2d.size()];
        for(int i = 0; i < grandTableau2d.size(); i++) {
			ArraylistCard2d card2d = grandTableau2d.get(i);

			if(card2d.isNullCard()) {
				newGrandTableau[i] = null;
			}else {
				newGrandTableau[i] = (Card) card2d.getCard();
				
			}
		}
        manualModel.setGrandTableau(newGrandTableau);

        manualModel.setIndicePremierElement(indexOfSmallest);
        
        manualModel.setIndiceDernierElement(indexOfBiggest);
        
        msgManualExecutionStep.setManualModel(manualModel);
        msgManualExecutionStep.send();
        
    }

    private void addNullCardAtDraggedCardOriginalLocation(List<ArraylistCard2d> cards) {
    	ArraylistCard2d draggedCardLocation = new ArraylistCard2d(new NullCard());
		draggedCardLocation.setTopLeftX(draggedCardOriginalTopLeftX);
		draggedCardLocation.setTopLeftY(draggedCardOriginalTopLeftY);

		cards.add(draggedCardLocation);
	}
    private void addBottomCards(List<ArraylistCard2d> cards, 
            List<ArraylistCard2d> bottomCards2d) {
            for(ArraylistCard2d card:cards) {
            	if(card.topLeftY()>=CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS) {
            		bottomCards2d.add(card);
            	}
            }
    }
    
    private void insertDraggedCardAtFinalLocation(List<ArraylistCard2d> cards) {
		
    	int indexOfClosestCard = indexOfClosestCard(cards, draggedCard);
		cards.set(indexOfClosestCard, draggedCard);
	
    }
    
    private int indexOfClosestCard(List<ArraylistCard2d> cards, ArraylistCard2d draggedCard) {
		double distance = Double.MAX_VALUE;
		int index = -1;
		
		for(int i = 0; i < cards.size(); i++) {
			ArraylistCard2d card = cards.get(i);

			double distanceThisCard = distance(card, draggedCard);
			
			if(distanceThisCard < distance) {
				distance = distanceThisCard;
				index = i;
			}
		}
		
		return index;
	}
    private void orderCardsLeftToRight(List<ArraylistCard2d> topCards2d) {
		topCards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});
	}
    
    private List<ArraylistCard2d> collectCards(){
		List<ArraylistCard2d> cards = new ArrayList<>();

		for(Object2d object2d : getObjects()) {
			if(object2d instanceof ArraylistCard2d
					) {
				cards.add((ArraylistCard2d) object2d);
			}
		}
		
		return cards;
	}
    
    private double distance(ArraylistCard2d cardA, ArraylistCard2d cardB) {
		
    	return Math.sqrt(Math.pow(cardA.topLeftX() - cardB.topLeftX(),2) + Math.pow(cardA.topLeftY() - cardB.topLeftY(), 2));
	}
    private void forgetDraggedCard() {
		draggedCard = null;
	}
}
