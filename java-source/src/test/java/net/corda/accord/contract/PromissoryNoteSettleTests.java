package net.corda.accord.contract;

import net.corda.core.contracts.Amount;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.PartyAndReference;
import net.corda.core.contracts.TypeOnlyCommandData;
import net.corda.core.identity.AbstractParty;
import net.corda.core.utilities.OpaqueBytes;
import net.corda.finance.contracts.asset.Cash;
import net.corda.testing.node.MockServices;

import java.util.Arrays;
import java.util.Currency;

import static net.corda.testing.node.NodeTestUtils.ledger;

/**
 * TODO: Modify tests to adapt from IOU to promissory note.
 */

public class PromissoryNoteSettleTests {

    public interface Commands extends CommandData {
	    class DummyCommand extends TypeOnlyCommandData implements Commands{}
    }

    static private final MockServices ledgerServices = new MockServices(Arrays.asList("net.corda.accord"));

	private Cash.State createCashState(AbstractParty owner, Amount<Currency> amount) {
        OpaqueBytes defaultBytes = new OpaqueBytes(new byte[1]);
	    PartyAndReference partyAndReference = new PartyAndReference(owner, defaultBytes);
	    return new Cash.State(partyAndReference, amount, owner);
    }

    /**
     * Task 1.
     * We need to add another case to deal with settling in the [PromissoryNoteCordaContract.verify] function.
     * TODO: Add the [PromissoryNoteCordaContract.Commands.Settle] case to the verify function.
     * Hint: You can leave the body empty for now.
     */
//    @Test
//    public void mustIncludeSettleCommand() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.POUNDS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State inputCash = createCashState(BOB.getParty(), Currencies.POUNDS(5));
//        OwnableState outputCash = inputCash.withNewOwner(ALICE.getParty()).getOwnableState();
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.POUNDS(5)));
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, inputCash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, outputCash);
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                return tx.failsWith("Contract Verification Failed");
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.POUNDS(5)));
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, inputCash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, outputCash);
//                tx.command(BOB.getPublicKey(), new Commands.DummyCommand());
//                return tx.failsWith("Contract verification failed");
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.POUNDS(5)));
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, inputCash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, outputCash);
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                return tx.verifies();
//            });
//            return null;
//        });
//
//    }

    /**
     * Task 2.
     * For now, we only want to settle one IOU at once. We can use the [TransactionForContract.groupStates] function
     * to group the IOUs by their [linearId] property. We want to make sure there is only one group of input and output
     * IOUs.
     * TODO: Using [groupStates] add a constraint that checks for one group of input/output IOUs.
     * Hint:
     * - The [groupStates] method on a Transaction takes two type parameters: the type of the state you wish to group by and the type
     *   of the grouping key used (indicated by a method reference), in this case as you need to use the [linearId] and it is a [UniqueIdentifier].
     *
     *       tx.groupStates(State.class, State::getLinearId)
     *
     */
//    @Test
//    public void mustBeOneGroupOfIOUs() {
//        PromissoryNoteState iouONE = new PromissoryNoteState(Currencies.POUNDS(10), ALICE.getParty(), BOB.getParty());
//        PromissoryNoteState iouTWO = new PromissoryNoteState(Currencies.POUNDS(5), ALICE.getParty(), BOB.getParty());
//        Cash.State inputCash = createCashState(BOB.getParty(), Currencies.POUNDS(5));
//        CommandAndState outputCash = inputCash.withNewOwner(ALICE.getParty());
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouONE);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouTWO);
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouONE.pay(Currencies.POUNDS(5)));
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, inputCash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, outputCash.getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.failsWith("List has more than one element.");
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouONE);
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouONE.pay(Currencies.POUNDS(5)));
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, inputCash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, outputCash.getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.verifies();
//                return null;
//            });
//            return null;
//        });
//
//    }

    /**
     * Task 3.
     * There always has to be one input IOU in a settle transaction but there might not be an output IOU.
     * TODO: Add a constraint to check there is always one input IOU.
     */

//    @Test
//    public void mustHaveOneInputIOU() {
//
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.POUNDS(10), ALICE.getParty(), BOB.getParty());
//        PromissoryNoteState iouOne = new PromissoryNoteState(Currencies.POUNDS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State tenPounds = createCashState( BOB.getParty(), Currencies.POUNDS(10));
//        Cash.State fivePounds = createCashState( BOB.getParty(), Currencies.POUNDS(5));
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.failsWith("There must be one input IOU.");
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fivePounds);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fivePounds.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.verifies();
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouOne);
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenPounds);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenPounds.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.verifies();
//                return null;
//            });
//            return  null;
//        });
//
//    }

    /**
     * Task 4.
     * Now we need to ensure that there are cash states present in the outputs list. The [PromissoryNoteCordaContract] doesn't care
     * about input cash as the validity of the cash transaction will be checked by the [Cash] contract. We do however
     * need to count how much cash is being used to settle and update our [PromissoryNoteState] accordingly.
     * TODO: Filter out the cash states from the list of outputs list and assign them to a constant.
     * Hint:
     * - Use the [outputsOfType] extension function to filter the transaction's outputs by type, in this case [Cash.State].
     */

//    @Test
//    public void mustBeCashOutputStatesPresent() {
//
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.DOLLARS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State cash = createCashState(BOB.getParty(), Currencies.DOLLARS(5));
//        CommandAndState cashPayment = cash.withNewOwner(ALICE.getParty());
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("There must be output cash.");
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cashPayment.getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(ALICE.getPublicKey(), BOB.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.verifies();
//                return null;
//            });
//            return null;
//        });
//
//    }

    /**
     * Task 5.
     * Not only to we need to check that [Cash] output states are present but we need to check that the payer is
     * correctly assigning the lender as the new owner of these states.
     * TODO: Add a constraint to check that the lender is the new owner of at least some output cash.
     * Hint:
     * - Not all of the cash may be assigned to the lender as some of the input cash may be sent back to the borrower as change.
     * - We need to use the [Cash.State.getOwner()] method to check to see that it is the value of our public key.
     * - Use [filter] to filter over the list of cash states to get the ones which are being assigned to us.
     * - Once we have this filtered list, we can sum the cash being paid to us so we know how much is being settled.
     */

//    @Test
//    public void mustBeCashOutputStatesWithRecipientAsOwner() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.POUNDS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State cash = createCashState(BOB.getParty(), Currencies.POUNDS(5));
//        CommandAndState invalidCashPayment = cash.withNewOwner(CHARLIE.getParty());
//        CommandAndState validCashPayment = cash.withNewOwner(ALICE.getParty());
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.POUNDS(5)));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, invalidCashPayment.getOwnableState());
//                tx.command(BOB.getPublicKey(), invalidCashPayment.getCommand());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("There must be output cash paid to the recipient.");
//               return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cash);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.POUNDS(5)));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, validCashPayment.getOwnableState());
//                tx.command(BOB.getPublicKey(), validCashPayment.getCommand());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.verifies();
//                return null;
//            });
//            return null;
//        });
//
//    }

    /**
     * Task 6.
     * Now we need to sum the cash which is being assigned to the lender and compare this total against how much of the iou is
     * left to pay.
     * TODO: Add a constraint that checks the lender cannot be paid more than the remaining IOU amount left to pay.
     * Hint:
     * - The remaining amount of the IOU is the amount less the paid property.
     * - To sum a list of [Cash.State]s, collect all [Cash.States] assigned to the lender and use a reduce method or
     * = explicitly loop through the list to sum the individual states.
     * - We can compare the amount left paid to the amount being paid to use, ensuring the amount being paid isn't too much.
     */

//    @Test
//    public void cashSettlementAmountMustBeLessThanRemainingIOUAmount() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.DOLLARS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State elevenDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(11));
//        Cash.State tenDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(10));
//        Cash.State fiveDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(5));
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, elevenDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(11)));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, elevenDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("The amount settled cannot be more than the amount outstanding.");
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.verifies();
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.verifies();
//                return null;
//            });
//            return null;
//        });
//    }

    /**
     * Task 7.
     * Your Java implementation should handle this for you but it goes without saying that we should only be able to settle
     * in the currency that the IOU in denominated in.
     * TODO: You shouldn't have anything to do here but here are some tests just to make sure!
     */

//    @Test
//    public void cashSettlementMustBeInTheCorrectCurrency() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.DOLLARS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State tenDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(10));
//        Cash.State tenPounds = createCashState( BOB.getParty(), Currencies.POUNDS(10));
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenPounds);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenPounds.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("Token mismatch: GBP vs USD");
//               return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.verifies();
//                return null;
//            });
//            return null;
//        });
//    }

    /**
     * Task 8.
     * If we fully settle the IOU, then we are done and thus don't require one on ledgerServices.ledger anymore. However, if we only
     * partially settle the IOU, then we want to keep the IOU on ledger with an amended [paid] property.
     * TODO: Write a constraint that ensures the correct behaviour depending on the amount settled vs amount remaining.
     * Hint: You can use a simple if statement and compare the total amount paid vs amount left to settle.
     */

//    @Test
//    public void mustOnlyHaveOutputIOUIfNotFullySettling() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.DOLLARS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State tenDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(10));
//        Cash.State fiveDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(5));
//            ledger(ledgerServices, l -> {
//                l.transaction(tx -> {
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                    tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                    tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                    tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                    tx.failsWith("There must be one output IOU.");
//                    return null;
//                });
//                l.transaction(tx -> {
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                    tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                    tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                    tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                    tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                    tx.verifies();
//                    return null;
//                });
//                l.transaction(tx -> {
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars);
//                    tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(10)));
//                    tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                    tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                    tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                    tx.failsWith("There must be no output IOU as it has been fully settled.");
//                    return null;
//                });
//                l.transaction(tx -> {
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                    tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars);
//                    tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, tenDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                    tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                    tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                    tx.verifies();
//                    return null;
//                });
//                return null;
//            });
//    }

    /**
     * Task 9.
     * We want to make sure that the only property of the IOU which changes when we settle, is the paid amount.
     * TODO: Write a constraint to check only the paid property of the [PromissoryNoteState] changes when settling.
     */

//    @Test
//    public void onlyPaidPropertyMayChange() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.DOLLARS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State fiveDollars = createCashState( BOB.getParty(), Currencies.DOLLARS(5));
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                PromissoryNoteState iouCopy = iou.copy(iou.amount, iou.lender, CHARLIE.getParty(), iou.paid).pay(Currencies.DOLLARS(5));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouCopy);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("The borrower may not change when settling.");
//                return null;
//            });
//
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                PromissoryNoteState iouCopy = iou.copy(Currencies.DOLLARS(0), iou.lender, CHARLIE.getParty(), iou.paid).pay(Currencies.DOLLARS(5));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouCopy);
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("The amount may not change when settling.");
//                return null;
//            });
//
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                PromissoryNoteState iouCopy = iou.copy(iou.amount, CHARLIE.getParty(), iou.borrower, iou.paid).pay(Currencies.DOLLARS(5));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouCopy);
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("The lender may not change when settling.");
//                return null;
//            });
//
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, fiveDollars.withNewOwner(ALICE.getParty()).getOwnableState());
//                PromissoryNoteState iouCopy = iou.copy(iou.amount, iou.lender, iou.borrower, iou.paid).pay(Currencies.DOLLARS(5));
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iouCopy);
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.verifies();
//                return null;
//            });
//
//            return null;
//        });
//
//    }

    /**
     * Task 10.
     * Both the lender and the borrower must have signed an IOU issue transaction.
     * TODO: Add a constraint to the contract code that ensures this is the case.
     */

//    public void mustBeSignedByAllParticipants() {
//        PromissoryNoteState iou = new PromissoryNoteState(Currencies.DOLLARS(10), ALICE.getParty(), BOB.getParty());
//        Cash.State cash = createCashState(BOB.getParty(), Currencies.DOLLARS(5));
//        CommandAndState cashPayment = cash.withNewOwner(ALICE.getParty());
//
//        ledger(ledgerServices, l -> {
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cash);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cashPayment.getOwnableState());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(ALICE.getPublicKey(), CHARLIE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("Both lender and borrower together only must sign IOU settle transaction.");
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cash);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cashPayment.getOwnableState());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(BOB.getPublicKey(), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("Both lender and borrower together only must sign IOU settle transaction.");
//                return null;
//            });
//            l.transaction(tx -> {
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cash);
//                tx.input(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou);
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, cashPayment.getOwnableState());
//                tx.output(PromissoryNoteCordaContract.IOU_CONTRACT_ID, iou.pay(Currencies.DOLLARS(5)));
//                tx.command(BOB.getPublicKey(), new Cash.Commands.Move());
//                tx.command(Arrays.asList(BOB.getPublicKey(), ALICE.getPublicKey()), new PromissoryNoteCordaContract.Commands.Settle());
//                tx.failsWith("Both lender and borrower together only must sign IOU settle transaction.");
//                return null;
//            });
//            return null;
//        });
//
//    }

}