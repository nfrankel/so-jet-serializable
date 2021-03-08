package ch.frankel.so;

import java.io.Serializable;

public class PageVisit1 implements Serializable {

    /**
     *
     */
    private int number;
    private String pageName;

    public PageVisit1() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        result = prime * result + ((pageName == null) ? 0 : pageName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PageVisit1 other = (PageVisit1) obj;
        if (number != other.number)
            return false;
        if (pageName == null) {
            if (other.pageName != null)
                return false;
        } else if (!pageName.equals(other.pageName))
            return false;
        return true;
    }

    public PageVisit1(int number, String pageName) {
        super();
        this.number = number;
        this.pageName = pageName;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the pageName
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * @param pageName the pageName to set
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

}
