package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

/**
 * Created by Мария on 29.03.2017.
 */
@RunWith(Parameterized.class)
public class CustomListsTest {

    private List<String> list;

    public CustomListsTest(List<String> list) {
        this.list = list;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new CustomArrayList(),
                new CustomLinkedList()
        });
    }

    @Before
    public void init() {
        list.clear();
    }

    @Test
    public void testThatNewListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testThatListNotEmptyAfterAddingElement() {
        list.add("aaaa");
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void testThatListContainsElementThatWeAddedBefore() {
        String value = "bbb";

        list.add(value);

        assertTrue(list.contains(value));
    }

    @Test
    public void testThatListNotContainsElementThatWasntAddedToList() throws Exception {
        list.add("fff");
        assertFalse(list.contains("ccc"));
    }

    @Test
    public void testThatListContainsNullIfItWasAdded() {
        list.add("fff");
        list.add(null);

        assertTrue(list.contains(null));
    }

    @Test
    public void testThatListNotContainsNullIfItWasNotAdded() {
        list.add("fff");
        assertFalse(list.contains(null));
    }

    @Test
    public void testThatListsSizeIsDynamic() throws Exception {
        int size = 50;

        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i));
        }

        assertThat(list.size(), is(size));
    }

    @Test
    public void testThatWeCanGetElementByIndex() {

        fillList();

        assertThat(list.get(1), is(equalTo("aa1a")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantGetElementByIndexMoreThenSize() throws Exception {

        fillList();

        list.get(list.size());
    }

    @Test
    public void testThatWeCanRemoveExistedElementFromList() throws Exception {
        fillList();

        list.remove("ssss");

        assertFalse("contains", list.contains("ssss"));
    }

    @Test
    public void testThatWeCanDeleteElementByIndex() throws Exception {
        fillList();

        String removed = list.remove(3);

        assertFalse(list.contains("ssss"));
        assertThat(removed, is(equalTo("ssss")));
    }

    @Test
    public void testThatWeCanDeleteLastElement() throws Exception {
        fillList();

        int prevSize = list.size();

        list.remove(list.size() - 1);

        assertFalse(list.contains("aa4a"));
        assertThat(list.size(), is(equalTo(prevSize - 1)));
    }

    @Test
    public void testThatWeCantDeleteNonExistentElement() throws Exception {
        fillList();

        assertFalse(list.remove("sadasdasd"));
    }

    @Test
    public void testThatWeCanSetNewElementOnPosition() throws Exception {
        fillList();

        list.set(2, "bbbb");

        assertThat(list.get(2), is("bbbb"));
    }

    @Test
    public void testThatSetMethodReturnsPreviousValue() throws Exception {
        fillList();

        assertThat(list.set(3, "cccc"), is("ssss"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantSetElementByIndexMoreThenSize() {
        fillList();

        list.set(33, "cccc");
    }

    @Test
    public void testThatWeCanAddByIndex() throws Exception {
        fillList();

        list.add(2, "hey");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        assertThat(list.get(2), is("hey"));
        assertThat(list.get(3), is("aa2a"));
        assertThat(list.size(), is(8));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantAddElementByIndexMoreThenSize() {
        list.add(12, "aa2a");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantRemoveElementByIndexMoreThenSize() {
        fillList();

        list.remove(8);

    }

    @Test
    public void testThatWeCanGetASublistFromList() {
        fillList();

        List<String> testList = list.subList(2, 5);

        assertThat(testList.get(0), is("aa2a"));
        assertThat(testList.get(1), is("ssss"));
        assertThat(testList.get(2), is("aa3a"));
        assertThat(testList.get(3), is("aa2a"));
        assertThat(testList.size(), is(4));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantGetSublistByIndexMoreThenSizeOrLessThanZeroOrIfStartIndexIsLessThanEndIndex() {
        fillList();

        list.subList(-1, 3);
        list.subList(2, 30);
        list.subList(12, 8);


    }

    @Test
    public void testThatWeCanCheckIndexOfMethod() {
        fillList();

        assertThat(list.indexOf("aa2a"), is(2));
    }

    @Test
    public void testThatIfWeAddNullValueIndexOfWillWorkCorrectly() {
        list.add("aa0a");
        list.add("aa1a");
        list.add(null);

        assertThat(list.indexOf(null), is(2));
    }

    @Test
    public void testThatIfThereIsNoMatchesInIndexOfMethodWillReturnMinusOne() {
        fillList();

        assertThat(list.indexOf("test"), is(-1));
    }

    @Test
    public void testThatWeCanCheckLastIndexOfMethod() {
        fillList();

        assertThat(list.lastIndexOf("aa2a"), is(5));
        assertThat(list.lastIndexOf("aa0a"), is(0));
    }

    @Test
    public void testThatIfWeAddNullValueLastIndexOfWillWorkCorrectly() {
        list.add("aa0a");
        list.add("aa1a");
        list.add(null);

        assertThat(list.lastIndexOf(null), is(2));
    }

    @Test
    public void testThatIfThereIsNoMatchesInLastIndexOfMethodWillReturnMinusOne() {
        fillList();

        assertThat(list.lastIndexOf("test"), is(-1));
    }

    @Test
    public void testThatWeCanAddDataToAnotherArray() {
        fillList();

        Object[] testArray = list.toArray();

        Object[] testArray2 = list.toArray(new String[list.size()]);

        for (int i = 0; i < testArray.length; i++) {
            assertThat(testArray[2], is("aa2a"));
            assertThat(testArray2[3], is("ssss"));
        }
    }

    @Test
    public void testThatIfOurListContainsAllElementsOfTheOtherCollectionWillReturnTrue() {
        fillList();

        ArrayList<String> str = new ArrayList<>();
        str.add("ssss");
        str.add("aa1a");
        str.add("aa2a");
        assertThat(list.containsAll(str), is(true));

    }

    @Test
    public void testThatIfOurListNotContainsAllElementsOfTheOtherCollectionWillReturnFalse() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("ssss");
        arr.add("aavv1a");
        arr.add("aa2a");
        assertThat(list.containsAll(arr), is(false));

    }

    @Test
    public void testThatIfAnotherCollectionContainsNullOrOtherElementsTypeWillReturnFalse() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(null);

        assertThat(list.containsAll(arr), is(false));
    }

    @Test
    public void testThatWeCanAddAllFromOtherCollection() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("ssss");
        arr.add("aavv1a");
        arr.add("aa2a");

        assertThat(list.addAll(arr), is(true));
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(2), is("aa2a"));
            assertThat(list.get(9), is("aa2a"));
        }
    }

    @Test
    public void testThatWeCanAddAllFromOtherCollectionByIndex() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("aaa");
        arr.add("bbb");
        arr.add("ccc");

        assertThat(list.addAll(2, arr), is(true));
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(2), is("ccc"));
            assertThat(list.get(9), is("aa4a"));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantAddAllFromOtherCollectionByIndexMoreThenSize() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("aaa");
        arr.add("bbb");
        arr.add("ccc");

        assertThat(list.addAll(3424, arr), is(false));
    }

    @Test
    public void testThatWeCanRemoveAllFromOtherCollection() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("aa0a");
        arr.add("ssss");
        arr.add("aa2a");

        assertThat(list.removeAll(arr), is(true));
        for (int i = 0; i < list.size(); i++) {
            assertFalse(list.contains("aa0a"));
            assertFalse(list.contains("ssss"));
        }
    }

    @Test
    public void testThatWeCantRemoveAllFromAnotherCollectionIfThereIsNoSuchElementInOurList() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("rrrrrrr");

        assertThat(list.removeAll(arr), is(false));
    }

    @Test
    public void testThatWeCanRetainAllFromOtherCollection() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("aa0a");
        arr.add("ssss");
        arr.add("aa2a");

        list.retainAll(arr);
        assertThat(list.retainAll(arr), is(true));
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.contains("aa2a"));
            assertTrue(list.contains("ssss"));
            assertFalse(list.contains("aa1a"));
            assertFalse(list.contains("aa3a"));
        }
    }

    @Test
    public void testThatWeCantRetainAllFromAnotherCollectionIfThereIsNoSuchElementInOurList() {
        fillList();

        ArrayList<String> arr = new ArrayList<>();
        arr.add("rrrrrrr");

        assertThat(list.retainAll(arr), is(false));
    }

    @Test
    public void iteratorTest() {
        fillList();

        Iterator<String> iter = list.iterator();
            iter.next();
            assertThat(iter.hasNext(), is(true));
            assertThat(iter.next(), is("aa1a"));
            iter.next();
            iter.remove();
            assertThat(list.contains("ssss"), is(false));
        }

    @Test
    public void listIteratorTest() {
        fillList();

        ListIterator<String> iter = list.listIterator();
        iter.next();
        assertThat(iter.hasNext(), is(true));
        iter.set("ddd");
        assertThat(list.contains("ddd"), is(true));
        assertThat(list.contains("aa1a"), is(false));
        iter.add("zzz");
        assertThat(list.contains("zzz"), is(true));
        iter.next();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.nextIndex(), is(4));
        assertThat(iter.hasPrevious(), is(true));
        assertThat(iter.previousIndex(), is(2));
        iter.previous();
        assertThat(iter.previousIndex(), is(1));
        iter.next();
        iter.remove();
        assertThat(list.contains("aa1a"), is(false));

    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void listIteratorTestLessThanSize() {
        fillList();

        ListIterator<String> iter = list.listIterator();

        iter.previous();
        iter.previous();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void listIteratorTestMoreThanSize() {
        fillList();

        ListIterator<String> iter = list.listIterator();

        while (true){
            iter.next();
        }
    }

    @Test
    public void listIteratorTestByIndex() {
        fillList();

        ListIterator<String> iter = list.listIterator(3);
        assertThat(iter.hasNext(), is(true));
        iter.next();
        iter.add("zzz");
        iter.remove();
        assertThat(list.contains("aa3a"), is(false));
        assertThat(iter.hasPrevious(), is(true));
        assertThat(iter.previousIndex(), is(3));
        iter.set("vvv");
        assertThat(list.contains("vvv"), is(true));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void listIteratorTestByIndexOutOfBounds() {
        fillList();

        ListIterator<String> iter = list.listIterator(20);
        iter.next();

    }
    private void fillList() {
        list.add("aa0a");
        list.add("aa1a");
        list.add("aa2a");
        list.add("ssss");
        list.add("aa3a");
        list.add("aa2a");
        list.add("aa4a");
    }
}