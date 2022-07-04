package atividade3;

/**
 *
 * @author talisson
 */
public class Conversao {

    public TreeNode arrayParaBTS(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode raiz = arrayToTreeConverter(num, 0, num.length - 1);
        return raiz;
    }

    public TreeNode arrayToTreeConverter(int[] num, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode raiz = new TreeNode(num[mid]);
        raiz.esq = arrayToTreeConverter(num, low, mid - 1);
        raiz.dir = arrayToTreeConverter(num, mid + 1, high);
        return raiz;
    }
}
