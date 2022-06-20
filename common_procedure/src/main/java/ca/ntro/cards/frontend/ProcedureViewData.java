// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.frontend;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.world2d.CommonObject2d;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.models.world2d.ProcedureCard2d;
import ca.ntro.cards.models.world2d.ProcedureDrawingOptions;
import ca.ntro.cards.models.world2d.ProcedureMarker2d;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;
import ca.ntro.core.stream.Stream;

public abstract class   ProcedureViewData<OBJECT2D extends ProcedureObject2d<OBJECT2D, WORLD2D, OPTIONS>,
                                          WORLD2D  extends ProcedureWorld2d<OBJECT2D, WORLD2D, OPTIONS>,
                                          OPTIONS  extends ProcedureDrawingOptions>

                extends CommonViewData<OBJECT2D, WORLD2D, OPTIONS> {

	private boolean isExecutionReplayInProgress = false;
	private double timeSinceLastReplayStep;
	private MsgExecutionStepForward msgExecutionStepForward = NtroApp.newMessage(MsgExecutionStepForward.class);

	public void onTimePasses(double secondsElapsed) {
		if(isExecutionReplayInProgress) {

			timeSinceLastReplayStep -= secondsElapsed;
			
			if(timeSinceLastReplayStep < 0) {
				timeSinceLastReplayStep = CommonConstants.SECONDS_BETWEEN_EXECUTION_STEPS;
				msgExecutionStepForward.send();
			}
		}

		super.onTimePasses(secondsElapsed);
	}

	public void startExecutionReplay() {
		isExecutionReplayInProgress = true;
		timeSinceLastReplayStep = CommonConstants.SECONDS_BETWEEN_EXECUTION_STEPS;
	}

	public void stopExecutionReplay() {
		isExecutionReplayInProgress = false;
	}

	public void addOrUpdateMarker(String markerId, double topLeftX, double topLeftY) {
		
		ProcedureMarker2d<OBJECT2D, WORLD2D, OPTIONS> marker2d = null;

		marker2d = (ProcedureMarker2d) world2d().objectById(markerId);

		if(marker2d == null) {
			marker2d = new ProcedureMarker2d(markerId);
			world2d().addObject2d((OBJECT2D) marker2d);
		}
		
		marker2d.setTopLeftX(topLeftX);
		marker2d.setTopLeftY(topLeftY);
		
	}

	public void setCardFaceUp(AbstractCard card, boolean faceUp) {

		ProcedureCard2d card2d = (ProcedureCard2d) world2d().objectById(card.id());

		if(card2d != null) {
			card2d.setFaceUp(faceUp);
		}
	}

	public void displayCardFaceDown(AbstractCard card) {
		setCardFaceUp(card, false);
	}


	public void displayCardFaceUp(AbstractCard card) {
		setCardFaceUp(card, true);
	}

	public void removeNullCards() {
		Set<String> toRemove = new HashSet<>();
		
		for(CommonObject2d object2d : world2d.getObjects()) {
			if(object2d instanceof ProcedureCard2d) {
				if(((ProcedureCard2d) object2d).isNullCard()) {
					toRemove.add(object2d.id());
				}
			}
		}
		
		world2d.removeObject2dIn(toRemove);

	}

	public void addOrUpdateCard(AbstractCard card, double topLeftX, double topLeftY) {

		ProcedureCard2d card2d = (ProcedureCard2d) world2d().objectById(card.id());
		
		if(card2d == null) {
			card2d = newCard2d(card);
			world2d().addObject2d((OBJECT2D) card2d);
		}
		
		if(card.isNullCard()) {
			
			card2d.setTopLeftX(topLeftX);
			card2d.setTopLeftY(topLeftY);
			
		}else {
			
			card2d.setTarget(topLeftX, topLeftY);
		}
	}

	protected abstract ProcedureCard2d newCard2d(AbstractCard card);

	public void removeCardsNotIn(Stream<Card> cards) {
		Set<String> cardIds = new HashSet<>();

		cards.forEach(c -> {
			if(c != null) {
				cardIds.add(c.id());
			}
		});

		world2d.removeObject2dNotIn(cardIds);
	}


}
