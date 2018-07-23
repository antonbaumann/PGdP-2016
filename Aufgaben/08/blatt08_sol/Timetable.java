package pgdpws16;

public class Timetable {

    DateList dates;

    public Timetable() {
        dates = null;
    }

    /**
     * calculates end of @param date in minutes with respect to one week
     *
     * @return end of @param date in minutes
     */
    private static int dateStartsGlobal(Date date) {
        return (date.getWeekday() * 24 * 60) + (date.getStarthour() * 60) + date.getStartmin();
    }

    /**
     * calculates start of @param date in minutes with respect to one week
     *
     * @return start of @param date in minutes
     */
    private static int dateEndsGlobal(Date date) {
        return dateStartsGlobal(date) + date.getDuration();
    }

    /**
     * adds a new Date to the timetable if there is no overlap with a date
     * already in the timetable
     *
     * @param newDate date that is added (if possible)
     * @return true, if date is added; false, if date is not added because a
     * date at the same time is already in the timetable
     */
    public boolean addDate(Date newDate) {
        // newDate lasts longer than one week
        if (newDate.getDuration() > 7 * 24 * 60)
            return false;

//        // version 1: newDate lasts longer than sunday midnight
//        // meaning: ignore these dates
//        if (dateEndsGlobal(newDate) > 7 * 24 * 60)
//            return false;
        // version 2: newDate lasts longer than sunday midnight
        // meaning: date continues on monday
        boolean dateSplitted = false;
        Date part1;
        Date part2 = null;
        if (dateEndsGlobal(newDate) > 7 * 24 * 60) {
            int diff = 7 * 24 * 60 - dateStartsGlobal(newDate); // duration till sunday midnight
            part1 = new Date(newDate.getWeekday(), newDate.getStarthour(), newDate.getStartmin(), diff, newDate.getTitle());
            part2 = new Date(0, 0, 0, newDate.getDuration() - diff, newDate.getTitle());
            if (dateEndsGlobal(part2) > dateStartsGlobal(dates.info))
                return false;
            dateSplitted = true;
            newDate = part1;
        }

        // empty DateList
        if (dates == null) {
            dates = new DateList(newDate);
            return true;
        }
        DateList tmp = dates;
        DateList insertAfter = null;
        boolean insert = true;
        // find position where to add newDate - if possible
        while (tmp != null) {
            // dates that end before newDate starts
            if (dateEndsGlobal(tmp.info) <= dateStartsGlobal(newDate)) {
                insertAfter = tmp;
                tmp = tmp.next;
            } // dates that start after newDate ends
            else if (dateEndsGlobal(newDate) <= dateStartsGlobal(tmp.info)) {
                tmp = tmp.next;
            } // otherwise we have an overlap
            else {
                insert = false;
                break;
            }
        }

        if (!insert)
            return false;
        // insert newDate as first element in the list
        if (insertAfter == null) {
            DateList newList = new DateList(newDate);
            newList.append(dates);
            dates = newList;
            return true;
        }
        // insert newDate in the list (not as first element)
        dates.addAfter(insertAfter, new DateList(newDate));
        // insert part2 if neccessary
        if (dateSplitted) {
            DateList newList = new DateList(part2);
            newList.append(dates);
            dates = newList;
        }
        return true;
    }

    /**
     * @return the DateList element that is semantically equivalent to @param
     * date; null if no such element is in the timetable
     */
    private DateList findDate(Date date) {

        DateList it = dates;
        boolean isContained = false;
        while (it != null) {
            if (it.info.getWeekday() == date.getWeekday() && it.info.getTitle().equals(date.getTitle())
                    && it.info.getStarthour() == date.getStarthour() && it.info.getStartmin() == date.getStartmin()
                    && it.info.getDuration() == date.getDuration()) {
                isContained = true;
                break;
            }
            it = it.next;
        }
        return isContained ? it : null;
    }

    /**
     * deletes the date in the timetable that is semantically equivalent to
     *
     * @param date
     *
     * @param date element that should be deleted from the timetable
     * @return true if date could be deleted; false if no equivalent date is
     * found
     */
    public boolean deleteDate(Date date) {

        // version 2: date lasts longer than sunday midnight
        // then date was splitted while it was inserted
        if (dateEndsGlobal(date) > 7 * 24 * 60) {
            int diff = 7 * 24 * 60 - dateStartsGlobal(date); // duration till sunday midnight
            Date part1 = new Date(date.getWeekday(), date.getStarthour(), date.getStartmin(), diff, date.getTitle());
            Date part2 = new Date(0, 0, 0, date.getDuration() - diff, date.getTitle());
            if (findDate(part1) == null || findDate(part2) == null)
                return false;
            deleteDate(part1);
            deleteDate(part2);
            return true;
        }

        // delete dates that last not longer than sunday midnight
        DateList toDelete = findDate(date);
        if (toDelete == null)
            return false;
        if (dates == toDelete) {
            dates = dates.next;
            return true;
        }
        DateList it = dates;
        while (it.next != toDelete)
            it = it.next;
        it.next = toDelete.next;
        return true;

    }

    @Override
    public String toString() {
        if (dates == null)
            return "empty timetable";
        return dates.toString();
    }

    public static void main(String[] args) {
        Date test = new Date(0, 12, 15, 48 * 60, "test");
        Date pgdp = new Date(1, 12, 00, 3 * 60, "pgdp tutorial");
        Date info1 = new Date(2, 14, 00, 2 * 60, "info1 vorlesung");

        Timetable t = new Timetable();
        System.out.println(t);

        System.out.println("Date test: " + test);
        System.out.println("add test: " + t.addDate(test));
        System.out.println(t);

        System.out.println("Date pgdp: " + pgdp);
        System.out.println("add pgdp: " + t.addDate(pgdp));
        System.out.println(t);

        System.out.println("Date info1: " + info1);
        System.out.println("add info1: " + t.addDate(info1));
        System.out.println(t);

        Date infoVL = new Date(2, 14, 00, 2 * 60, "info1 vorlesung");
        System.out.println("Date infoVL: " + infoVL);
        System.out.println("delete infoVL: " + t.deleteDate(infoVL));
        System.out.println(t);

        Date sunMidnight = new Date(6, 23, 15, 4 * 60, "midnight brunch");
        System.out.println("Date sunMidnight: " + sunMidnight);
        System.out.println("add sunMidnight: " + t.addDate(sunMidnight));
        System.out.println(t);

        Date sunMid = new Date(6, 23, 15, 4 * 60, "midnight brunch");
        System.out.println("Date sunMid: " + sunMid);
        System.out.println("delete sunMid: " + t.deleteDate(sunMid));
        System.out.println(t);

    }

    private class DateList {

        Date info;
        DateList next;

        public DateList(Date info) {
            this.info = info;
        }

        public DateList(DateList list) {
            this.info = list.info;
            this.next = list.next;
        }

        private void addAfter(DateList node, DateList list) {
            DateList tmp = node.next;
            node.next = list;
            DateList it = node.next;
            while (it.next != null)
                it = it.next;
            it.next = tmp;
        }

        public void append(DateList list) {
            if (next == null) {
                next = list;
                return;
            }
            DateList tmp = next;
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = list;
        }

        @Override
        public String toString() {
            String tmp = info.toString();
            DateList p = next;
            while (p != null) {
                tmp = tmp + "\n" + p.info.toString();
                p = p.next;
            }
            return tmp;
        }
    }
}
