/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.swing;

/**
 *
 * @author Iuri Raiol
 */
public class ComboItem
{
    private String value;
    private String label;

    public ComboItem(String value, String label)
    {
        this.value = value;
        this.label = label;
    }
    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }
    
    @Override
    public String toString()
    {
        return label;
    }
}