/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ove.gui;

import java.util.List;
import javax.faces.model.ListDataModel;
//import com.mycompany.ove.model.School;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Malla
 */
public class SchoolDataModel {
/**
extends ListDataModel<School> implements SelectableDataModel<School> {

    public SchoolDataModel(List<School> data) {
        super(data);
    }

    @Override
    public Object getRowKey(School school) {
        return school.getName();
    }

    @Override
    public School getRowData(String rowKey) {
        List<School> cars = (List<School>) getWrappedData();

        for (School car : cars) {
            if (car.getName().equals(rowKey)) {
                return car;
            }
        }

        return null;
    }
    * **/
}
