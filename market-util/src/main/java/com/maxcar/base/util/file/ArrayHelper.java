//$Id: ArrayHelper.java,v 1.15 2005/07/19 18:17:15 oneovthafew Exp $
package com.maxcar.base.util.file;

import java.lang.reflect.Array;
import java.util.*;

/*
 * ===========================================================================
 * Copyright 2007 CHENGANG Corp. All Rights Reserved.
 * @version 1.0, ${date}
 * @author  Jack Chen 
 * ===========================================================================
 * 
 */
public final class ArrayHelper {
	
	
	public static int indexOf(Object[] array, Object object) {
		for ( int i=0; i<array.length; i++ ) {
			if ( array[i].equals(object) ) return i;
		}
		return -1;
	}

	public static String[] toStringArray(Object[] objects) {
		int length=objects.length;
		String[] result = new String[length];
		for (int i=0; i<length; i++) {
			result[i] = objects[i].toString();
		}
		return result;
	}

	public static String[] fillArray(String value, int length) {
		String[] result = new String[length];
		Arrays.fill(result, value);
		return result;
	}

	public static int[] fillArray(int value, int length) {
		int[] result = new int[length];
		Arrays.fill(result, value);
		return result;
	}


	public static String[] toStringArray(Collection coll) {
		return (String[]) coll.toArray(EMPTY_STRING_ARRAY);
	}
	
	public static String[][] to2DStringArray(Collection coll) {
		return (String[][]) coll.toArray( new String[ coll.size() ][] );
	}
	
	public static int[][] to2DIntArray(Collection coll) {
		return (int[][]) coll.toArray( new int[ coll.size() ][] );
	}
	

	public static int[] toIntArray(Collection coll) {
		Iterator iter = coll.iterator();
		int[] arr = new int[ coll.size() ];
		int i=0;
		while( iter.hasNext() ) {
			arr[i++] = ( (Integer) iter.next() ).intValue();
		}
		return arr;
	}

	public static boolean[] toBooleanArray(Collection coll) {
		Iterator iter = coll.iterator();
		boolean[] arr = new boolean[ coll.size() ];
		int i=0;
		while( iter.hasNext() ) {
			arr[i++] = ( (Boolean) iter.next() ).booleanValue();
		}
		return arr;
	}

	public static Object[] typecast(Object[] array, Object[] to) {
		return Arrays.asList(array).toArray(to);
	}

	//Arrays.asList doesn't do primitive arrays
	public static List toList(Object array) {
		if ( array instanceof Object[] ) return Arrays.asList( (Object[]) array ); //faster?
		int size = Array.getLength(array);
		ArrayList list = new ArrayList(size);
		for (int i=0; i<size; i++) {
			list.add( Array.get(array, i) );
		}
		return list;
	}

	public static String[] slice(String[] strings, int begin, int length) {
		String[] result = new String[length];
		for ( int i=0; i<length; i++ ) {
			result[i] = strings[begin+i];
		}
		return result;
	}

	public static Object[] slice(Object[] objects, int begin, int length) {
		Object[] result = new Object[length];
		for ( int i=0; i<length; i++ ) {
			result[i] = objects[begin+i];
		}
		return result;
	}

	public static List toList(Iterator iter) {
		List list = new ArrayList();
		while ( iter.hasNext() ) {
			list.add( iter.next() );
		}
		return list;
	}

	public static String[] join(String[] x, String[] y) {
		String[] result = new String[ x.length + y.length ];
		for ( int i=0; i<x.length; i++ ) result[i] = x[i];
		for ( int i=0; i<y.length; i++ ) result[i+x.length] = y[i];
		return result;
	}

	public static String[] join(String[] x, String[] y, boolean[] use) {
		String[] result = new String[ x.length + countTrue(use) ];
		for ( int i=0; i<x.length; i++ ) result[i] = x[i];
		int k = x.length;
		for ( int i=0; i<y.length; i++ ) {
			if ( use[i] ) result[k++] = y[i];
		}
		return result;
	}

	public static int[] join(int[] x, int[] y) {
		int[] result = new int[ x.length + y.length ];
		for ( int i=0; i<x.length; i++ ) result[i] = x[i];
		for ( int i=0; i<y.length; i++ ) result[i+x.length] = y[i];
		return result;
	}
	public static String toString( Object[] array ) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append( array[i] );
			if( i<array.length-1 ) sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	public static boolean isAllNegative(int[] array) {
		for ( int i=0; i<array.length; i++ ) {
			if ( array[i] >=0 ) return false;
		}
		return true;
	}

	public static boolean isAllTrue(boolean[] array) {
		for ( int i=0; i<array.length; i++ ) {
			if ( !array[i] ) return false;
		}
		return true;
	}

	public static int countTrue(boolean[] array) {
		int result=0;
		for ( int i=0; i<array.length; i++ ) {
			if ( array[i] ) result++;
		}
		return result;
	}

	public static boolean isAllFalse(boolean[] array) {
		for ( int i=0; i<array.length; i++ ) {
			if ( array[i] ) return false;
		}
		return true;
	}

	public static void addAll(Collection collection, Object[] array) {
		for ( int i=0; i<array.length; i++ ) {
			collection.add( array[i] );
		}
	}

	public static final String[] EMPTY_STRING_ARRAY = {};
	private ArrayHelper() {}
}






