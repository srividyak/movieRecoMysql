/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.baseClasses;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author srividyak
 */
public class utils<E, T> {

    List<T> comparatorBase;
    HashMap<Object, Integer> comparatorHashMap;
    Field field;

    public Object getObject(Object element, Field f) {
        String fieldName = f.getName();
        String getFieldMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Class elementClass = element.getClass();
        Object returnedObj = null;
        try {
            returnedObj = elementClass.getMethod(getFieldMethod).invoke(element);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnedObj;
    }
    
    
    private int compare(E a, E b) {
        Object oa = getObject(a, field);
        Object ob = getObject(b, field);
        if(comparatorHashMap.get(oa) < comparatorHashMap.get(ob)) {
            return 1;
        } else if(comparatorHashMap.get(oa) > comparatorHashMap.get(ob)) {
            return -1;
        } else {
            return 0;
        }
    }
    
    private int partition(E[] list, int i, int j) {
        int pivot = i;
        int length = list.length;
        while(i < j) {
            E ei = list[i];
            E ej = list[j];
            E ep = list[pivot];
            while(i<length && compare(list[i], list[pivot]) >= 0) {
                i++;
            }
            while(j >= 0 && compare(list[j], list[pivot]) < 0) {
                j--;
            }
            if(i < j) {
                E temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            } else {
                E temp = list[pivot];
                list[pivot] = list[j];
                list[j] = temp;
            }
        }
        return j;
    }
    
    private void qsort(E[] list, int low, int high) {
        if(low < high) {
            int partition = partition(list, low, high);
            qsort(list, low, partition - 1);
            qsort(list, partition + 1, high);
        }
    }
    
    private void mapComparator() {
        comparatorHashMap = new HashMap<Object, Integer>();
        if(this.comparatorBase != null) {
            int index = 0;
            for(T c : comparatorBase) {
                comparatorHashMap.put((Object) c, index++);
            }
        }
    }

    public List<E> customSort(List<T> comparatorBase, List<E> list, Field f) {
        this.comparatorBase = comparatorBase;
        this.field = f;
        this.mapComparator();
        E[] listArray = (E[]) list.toArray();
        this.qsort(listArray, 0, listArray.length - 1);
        return Arrays.asList(listArray);
    }
}
