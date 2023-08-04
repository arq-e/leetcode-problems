class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int skills = 0;
        Map<String, Integer> skillIndex = new HashMap<>();
        for (int i = 0; i < req_skills.length; ++i) {
            skills |= 1 << i;
            skillIndex.put(req_skills[i], i);
        }
        int[] peopleSkills = new int[people.size()];
        for (int i = 0; i < people.size(); ++i) {
            for (String k : people.get(i)) {
                peopleSkills[i] |= 1 << skillIndex.get(k);
            }
        }

        for (int i = 0; i < peopleSkills.length; ++i) {
            if (peopleSkills[i] == 0) continue;
            for (int j = i + 1; j < peopleSkills.length; ++j) {
                if (peopleSkills[j] == 0) continue;
                int sum = peopleSkills[i] | peopleSkills[j];
                if (sum == peopleSkills[i]) peopleSkills[j] = 0;
                else if (sum == peopleSkills[j])  {
                    peopleSkills[i] = 0;
                }
            }
        }

        Set<Integer> res = recursion(skills, peopleSkills, new HashSet<>(), 0, 0);

        return res != null ? res.stream().mapToInt(Integer::intValue).toArray() : new int[0];
    }

    private Set<Integer> recursion(int skills, int[] people, Set<Integer> picked, int curr, int num) {
        if (num == skills) return picked;
        if (curr >= people.length) return null;
        int copy = num|people[curr];

        picked.add(curr);
        Set<Integer> take = null;
        if (copy == skills) take = new HashSet(picked);
        else {
            int i = 1;
            while (curr + i < people.length && (copy|people[curr+i]) == copy) ++i;
            take = recursive(skills, people, picked, curr+i, copy);
        }

        picked.remove(curr);
        int i = 1;
        while (curr + i < people.length && (num|people[curr+i]) == num) ++i;
        Set<Integer> skip = recursive(skills, people, picked, curr+i, num);

        if (skip != null && take != null) {
            if (skip.size() < take.size()) return skip;
            return take;
        } else if (skip != null) {
            return skip;
        } else {
            return take;
        }
    }
}