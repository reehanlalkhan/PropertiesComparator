package org.ideoholic.propcompare.service;

import org.ideoholic.propcompare.utils.Constants;
import org.ideoholic.propcompare.utils.PropTableModel;
import org.ideoholic.propcompare.utils.SpringUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Reehan on 03-07-2015.
 */
public class DisplayService extends JPanel implements Constants {

    private JTable table;
    private JTextField filterText;
    private TableRowSorter<PropTableModel> sorter;

    public DisplayService(Map<String, List<String>> result) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        PropTableModel model = new PropTableModel(result);
        table = new JTable(model);
        sorter = new TableRowSorter<PropTableModel>(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 750));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setCellSelectionEnabled(true);
        // table.setAutoCreateRowSorter(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

        //Create a separate form for filterText and statusText
        JPanel form = new JPanel(new SpringLayout());
        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(l1);
        filterText = new JTextField();
        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        form.add(filterText);
        SpringUtilities.makeCompactGrid(form, 1, 2, 6, 6, 6, 6);
        add(form);
    }

    /**
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<PropTableModel, Object> rowFilter = null;
        final String searchText = filterText.getText();
        try {
            /*
            rowFilter = RowFilter.regexFilter(Pattern.compile(searchText, Pattern.CASE_INSENSITIVE).toString(), 0, 1, 2, 3);
            rowFilter = RowFilter.regexFilter(searchText);

            rowFilter = RowFilter.orFilter(Arrays.asList(RowFilter.regexFilter(searchText, 0),
            RowFilter.regexFilter(searchText, 1), RowFilter.regexFilter(searchText, 2), RowFilter.regexFilter(searchText, 3)));

            String regex = "(?i)^" + Pattern.quote(searchText) + "$";
            List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(4);
            filters.add(RowFilter.regexFilter(regex, 0));
            filters.add(RowFilter.regexFilter(regex, 1));
            filters.add(RowFilter.regexFilter(regex, 2));
            filters.add(RowFilter.regexFilter(regex, 3));
            rowFilter = RowFilter.andFilter(filters);

            List<RowFilter<Object,Object>> rfs = new ArrayList<RowFilter<Object,Object>>();
            rfs.add(RowFilter.regexFilter("(?i)" + searchText, 0,1,2,3));
            rowFilter = RowFilter.orFilter(rfs);

            List<RowFilter<PropTableModel, Object>> filters = new ArrayList<>(4);
            filters.add(
                    new RowFilter<PropTableModel, Object>() {
                        public boolean include(Entry entry) {
                            boolean cond = searchText.equalsIgnoreCase(entry.getStringValue(0));
                            if (cond)
                                System.out.println("One:" + entry.getStringValue(0));
                            return cond;
                        }
                    });
            filters.add(
                    new RowFilter<PropTableModel, Object>() {
                        public boolean include(Entry entry) {
                            boolean cond = searchText.equalsIgnoreCase(entry.getStringValue(1));
                            if (cond)
                                System.out.println("Two:" + entry.getStringValue(1));
                            return cond;
                        }
                    });
            filters.add(
                    new RowFilter<PropTableModel, Object>() {
                        public boolean include(Entry entry) {
                            boolean cond = searchText.equalsIgnoreCase(entry.getStringValue(2));
                            if (cond)
                                System.out.println("Three:" + entry.getStringValue(2));
                            return cond;
                        }
                    });
            filters.add(
                    new RowFilter<PropTableModel, Object>() {
                        public boolean include(Entry entry) {
                            boolean cond = searchText.equalsIgnoreCase(entry.getStringValue(3));
                            if (cond)
                                System.out.println("Four:" + entry.getStringValue(3));
                            return cond;
                        }
                    });
            rowFilter = RowFilter.orFilter(filters);
            */
            rowFilter = RowFilter.regexFilter(searchText);
        } catch (java.util.regex.PatternSyntaxException e) {
            System.out.println("java.util.regex.PatternSyntaxException: " + e);
            e.printStackTrace();
            return;
        }
        sorter.setRowFilter(rowFilter);
    }

}
