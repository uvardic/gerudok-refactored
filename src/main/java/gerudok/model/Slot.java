package gerudok.model;

import gerudok.controller.action.IconLoader;
import gerudok.model.device.Device;
import gerudok.model.link.Link;
import gerudok.model.observer.Observer;
import gerudok.model.visitor.TreeNodeModelVisitor;
import gerudok.view.Tree;
import gerudok.view.desktop.SlotPanel;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.enumeration;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Slot implements TreeNodeModel {

    private final Page page;

    private final String name;

    private final List<Element> elements = new ArrayList<>();

    private final List<Element> selectedElements = new ArrayList<>();

    public Slot(Page page) {
        this.page = page;
        this.name = formatName("Slot");

        this.page.addSlot(this);
    }

    public Slot(Page page, String name) {
        this.page = page;
        this.name = formatName(name);

        this.page.addSlot(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, page.getChildCount() + 1);
    }

    public void addElement(Element element) {
        if (element == null)
            throw new IllegalArgumentException("Element can't be null!");

        if (elements.contains(element))
            throw new IllegalArgumentException("Element is already present!");

        elements.add(element);
        Observer.updateSubject(Tree.class);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }

    List<Element> getElements() {
        return unmodifiableList(elements);
    }

    public void paintElements(Graphics2D graphics2D) {
        elements.forEach(element -> element.paint(graphics2D));
        selectedElements.forEach(selectedElement -> selectedElement.paintSelection(graphics2D));
        Observer.updateSubject(SlotPanel.class);
    }

    public boolean isElementAt(Point2D position) {
        return elements.stream().anyMatch(element -> element.isElementAt(position));
    }

    public Element getElementAt(Point2D position) {
        return elements.stream()
                .filter(element -> element.isElementAt(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Element not found for position: %s", position)
                ));
    }

    public List<Device<?>> getDevices() {
        return elements.stream()
                .filter(element -> element instanceof Device)
                .map(element -> (Device<?>) element)
                .collect(toList());
    }

    public boolean isDeviceAt(Point2D position) {
        return elements.stream()
                .filter(element -> element instanceof Device)
                .anyMatch(device -> device.isElementAt(position));
    }

    public Device<?> getDeviceAt(Point2D position) {
        return (Device<?>) elements.stream()
                .filter(element -> element instanceof Device)
                .filter(element -> element.isElementAt(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Device not found for position: %s", position)
                ));
    }

    public void selectElement(Element element) {
        if (element == null)
            throw new IllegalArgumentException("Element can't be null!");

        if (selectedElements.contains(element))
            throw new IllegalArgumentException("Element is already selected!");

        selectedElements.add(element);
        Observer.updateSubject(SlotPanel.class);
    }

    public void deselectElement(Element element) {
        selectedElements.remove(element);
        Observer.updateSubject(SlotPanel.class);
    }

    public void deselectAllElements() {
        selectedElements.clear();
        Observer.updateSubject(SlotPanel.class);
    }

    public boolean isElementSelected(Element element) {
        return selectedElements.contains(element);
    }

    public List<Device<?>> getSelectedDevices() {
        return selectedElements.stream()
                .filter(element -> element instanceof Device<?>)
                .map(element -> (Device<?>) element)
                .collect(toList());
    }

    public List<Link> getSelectedLinks() {
        return selectedElements.stream()
                .filter(element -> element instanceof Link)
                .map(element -> (Link) element)
                .collect(toList());
    }

    @Override
    public void acceptModelVisitor(TreeNodeModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Icon getIcon() {
        return IconLoader.SLOT_ICON.loadIcon();
    }

    @Override
    public Element getChildAt(int childIndex) {
        return elements.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return elements.size();
    }

    @Override
    public Page getParent() {
        return page;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Element))
            throw new IllegalArgumentException("Illegal child instance!");

        return elements.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends Element> children() {
        return enumeration(elements);
    }

    @Override
    public String toString() {
        return String.format("Slot{parent='%s', name=%s}", page, name);
    }

}
