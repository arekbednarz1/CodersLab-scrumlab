package pl.coderslab.model;

public class RecipePlanObj {

        //nie-obiektowo ew. pozniej pozamieniac
        private String dayName;
        private String mealName;
        private String recipeId;
        private String recipeName;
        private String recipeDescription;

        public RecipePlanObj() {
        }

        public RecipePlanObj(String dayName, String mealName, String recipeId, String recipeName, String recipeDescription) {
            this.dayName = dayName;
            this.mealName = mealName;
            this.recipeId = recipeId;
            this.recipeName = recipeName;
            this.recipeDescription = recipeDescription;
        }

        public String getDayName() {
            return dayName;
        }

        public void setDayName(String dayName) {
            this.dayName = dayName;
        }

        public String getMealName() {
            return mealName;
        }

        public void setMealName(String mealName) {
            this.mealName = mealName;
        }

        public String getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(String recipeId) {
            this.recipeId = recipeId;
        }

        public String getRecipeName() {
            return recipeName;
        }

        public void setRecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public String getRecipeDescription() {
            return recipeDescription;
        }

        public void setRecipeDescription(String recipeDescription) {
            this.recipeDescription = recipeDescription;
        }

        @Override
        public String toString() {
            return "RecipePlanNonObjShort{" +
                    "dayName='" + dayName + '\'' +
                    ", mealName='" + mealName + '\'' +
                    ", recipeId='" + recipeId + '\'' +
                    ", recipeName='" + recipeName + '\'' +
                    ", recipeDescription='" + recipeDescription + '\'' +
                    '}';
        }
    }



